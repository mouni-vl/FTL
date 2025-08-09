package com.example.fantasy.adapter.outbound.persistence;

import com.example.fantasy.application.port.out.PlayerRepository;
import com.example.fantasy.domain.model.MainPosition;
import com.example.fantasy.domain.model.Player;
import com.example.fantasy.domain.service.PlayerSearchCriteria;
import com.example.fantasy.adapter.outbound.persistence.entity.PlayerEntity;
import com.example.fantasy.adapter.outbound.persistence.mapper.PlayerEntityMapper;
import com.example.fantasy.adapter.outbound.persistence.repository.PlayerJpaRepository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Adapter implementing the PlayerRepository port using JPA.
 * This class delegates mapping operations to the PlayerEntityMapper.
 */
@Component
@RequiredArgsConstructor
@Transactional(readOnly = true)
@Slf4j
public class JpaPlayerAdapter implements PlayerRepository {

    private final PlayerJpaRepository playerRepo;
    private final PlayerEntityMapper mapper;
    
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Player> findAll() {
        return playerRepo.findAll().stream()
                .map(mapper::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public List<Player> saveAll(List<Player> players){
        log.debug("Saving {} players", players.size());
        List<PlayerEntity> entities = players.stream()
                .map(mapper::toSource)
                .collect(Collectors.toList());

        List<PlayerEntity> savedEntities = playerRepo.saveAll(entities);
        return savedEntities.stream()
                .map(mapper::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public List<Player> findAll(PlayerSearchCriteria criteria, int page, int size) {
        Specification<PlayerEntity> spec = createEntitySpecification(criteria);
        Pageable pageable = PageRequest.of(page, size);
        
        return playerRepo.findAll(spec, pageable)
                .stream()
                .map(mapper::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public Page<Player> findAll(Specification<Player> domainSpec, Pageable pageable) {
        log.debug("Finding players with specification");
        
        // Here's the key change - we need to adapt the domain spec to work with entities
        Specification<PlayerEntity> entitySpec;
        
        if (domainSpec != null) {
            log.debug("Using explicit criteria from specification");
            
            // Try to extract search criteria from the domain specification
            PlayerSearchCriteria extractedCriteria = extractSearchCriteria(domainSpec);
            entitySpec = createEntitySpecification(extractedCriteria);
            
            log.debug("Extracted criteria: {}", extractedCriteria);
        } else {
            log.debug("Using default specification (not deleted only)");
            entitySpec = (root, query, cb) -> cb.equal(root.get("deleted"), false);
        }
        
        // Apply the converted specification
        log.debug("Executing find with entity specification");
        Page<PlayerEntity> entityPage = playerRepo.findAll(entitySpec, pageable);
        log.debug("Found {} players matching criteria", entityPage.getTotalElements());
        
        // Map entities to domain objects
        List<Player> players = entityPage.getContent().stream()
                .map(mapper::toDomain)
                .collect(Collectors.toList());
        
        // Create new page with domain objects
        return new org.springframework.data.domain.PageImpl<>(
            players, pageable, entityPage.getTotalElements()
        );
    }
    
    @Override
    public long count(PlayerSearchCriteria criteria) {
        Specification<PlayerEntity> spec = createEntitySpecification(criteria);
        return playerRepo.count(spec);
    }

    @Override
    public Optional<Player> findById(Long id) {
        return playerRepo.findById(id)
                .map(mapper::toDomain);
    }
    
//    @Override
//    public Optional<Player> findByFmId(Integer fmId) {
//        return playerRepo.findByFmId(fmId)
//                .map(mapper::toDomain);
//    }

    @Override
    @Transactional
    public Player save(Player player) {
        log.debug("Saving player: {}", player.getFullName());
        PlayerEntity entity = mapper.toSource(player);
        PlayerEntity savedEntity = playerRepo.save(entity);
        return mapper.toDomain(savedEntity);
    }

    @Override
    @Transactional
    public void update(Player player) {
        if (player.getId() == null) {
            throw new IllegalArgumentException("Cannot update a player without an ID");
        }
        
        log.debug("Updating player ID: {}, fmId: {}", player.getId(), player.getId());
        
        Optional<PlayerEntity> existingEntityOpt = playerRepo.findById(player.getId());
        if (existingEntityOpt.isEmpty()) {
            throw new IllegalArgumentException("Player not found with ID: " + player.getId());
        }
        
        PlayerEntity existingEntity = existingEntityOpt.get();
        PlayerEntity updatedEntity = mapper.mergeToEntity(existingEntity, player);
        
        playerRepo.save(updatedEntity);
        log.debug("Player updated successfully: {}", player.getFullName());
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        playerRepo.softDeleteById(id);
    }

    /**
     * Extract PlayerSearchCriteria from a domain specification.
     * This is the key method to interpret the domain spec correctly.
     */
    private PlayerSearchCriteria extractSearchCriteria(Specification<Player> domainSpec) {
        PlayerSearchCriteria criteria = new PlayerSearchCriteria();
        
        try {
            // The spec object is actually a FilterCriteria<Player> implementation
            // We need to extract the filter properties directly
            
            // Get the actual implementation class
            Class<?> specClass = domainSpec.getClass();
            
            // If it's a proxy, get the superclass
            if (specClass.getName().contains("$Proxy")) {
                log.debug("Specification is a proxy, getting target");
                // This is specific to Spring Data JPA's implementation
                Field targetField = specClass.getSuperclass().getDeclaredField("target");
                targetField.setAccessible(true);
                Object target = targetField.get(domainSpec);
                specClass = target.getClass();
                log.debug("Target class: {}", specClass.getName());
            }
            
            // Try to get nameContains field if it exists
            try {
                Field nameField = specClass.getDeclaredField("nameContains");
                nameField.setAccessible(true);
                Object nameValue = nameField.get(domainSpec);
                if (nameValue != null) {
                    criteria.setNameContains((String) nameValue);
                    log.debug("Extracted nameContains: {}", nameValue);
                }
            } catch (NoSuchFieldException e) {
                log.debug("No nameContains field found in specification");
            }
            
            // Try to get mainPosition field
            try {
                Field posField = specClass.getDeclaredField("mainPosition");
                posField.setAccessible(true);
                Object posValue = posField.get(domainSpec);
                if (posValue != null) {
                    criteria.setMainPosition(MainPosition.valueOf(posValue.toString()));
                    log.debug("Extracted mainPosition: {}", posValue);
                }
            } catch (NoSuchFieldException e) {
                log.debug("No mainPosition field found in specification");
            }
            
            // Try to get club field
            try {
                Field clubField = specClass.getDeclaredField("club");
                clubField.setAccessible(true);
                Object clubValue = clubField.get(domainSpec);
                if (clubValue != null) {
                    criteria.setClub((String) clubValue);
                    log.debug("Extracted club: {}", clubValue);
                }
            } catch (NoSuchFieldException e) {
                log.debug("No club field found in specification");
            }
            
            // Try to get minPrice field
            try {
                Field priceField = specClass.getDeclaredField("minPrice");
                priceField.setAccessible(true);
                Object priceValue = priceField.get(domainSpec);
                if (priceValue != null) {
                    criteria.setMinPrice((Double) priceValue);
                    log.debug("Extracted minPrice: {}", priceValue);
                }
            } catch (NoSuchFieldException e) {
                log.debug("No minPrice field found in specification");
            }
            
            // Try to get maxPrice field
            try {
                Field priceField = specClass.getDeclaredField("maxPrice");
                priceField.setAccessible(true);
                Object priceValue = priceField.get(domainSpec);
                if (priceValue != null) {
                    criteria.setMaxPrice((Double) priceValue);
                    log.debug("Extracted maxPrice: {}", priceValue);
                }
            } catch (NoSuchFieldException e) {
                log.debug("No maxPrice field found in specification");
            }
            
            // Try to get availabilityStatus field
            try {
                Field statusField = specClass.getDeclaredField("availabilityStatus");
                statusField.setAccessible(true);
                Object statusValue = statusField.get(domainSpec);
                if (statusValue != null) {
                    criteria.setAvailabilityStatus((String) statusValue);
                    log.debug("Extracted availabilityStatus: {}", statusValue);
                }
            } catch (NoSuchFieldException e) {
                log.debug("No availabilityStatus field found in specification");
            }
            
        } catch (Exception e) {
            log.warn("Error extracting search criteria from specification", e);
        }
        
        return criteria;
    }

    /**
     * Creates a JPA Specification from domain search criteria
     */
    private Specification<PlayerEntity> createEntitySpecification(PlayerSearchCriteria criteria) {
        // Start with a specification that filters out deleted records
        Specification<PlayerEntity> spec = (root, query, cb) -> cb.equal(root.get("deleted"), false);
        
        if (criteria == null) {
            return spec;
        }
        
        log.debug("Creating specification from criteria: {}", criteria);
        
        // Name search (across first, second, and full name)
        if (criteria.getNameContains() != null && !criteria.getNameContains().trim().isEmpty()) {
            String pattern = "%" + criteria.getNameContains().toLowerCase() + "%";
            spec = spec.and((root, query, cb) -> 
                cb.or(
                    cb.like(cb.lower(root.get("fullName")), pattern),
                    cb.like(cb.lower(root.get("firstName")), pattern),
                    cb.like(cb.lower(root.get("secondName")), pattern)
                )
            );
            log.debug("Added name filter: {}", pattern);
        }
        
        // Position filter
        if (criteria.getMainPosition() != null) {
            spec = spec.and((root, query, cb) -> 
                cb.equal(root.get("mainPosition"), criteria.getMainPosition())
            );
            log.debug("Added position filter: {}", criteria.getMainPosition());
        }
        
        // Club filter
        if (criteria.getClub() != null && !criteria.getClub().trim().isEmpty()) {
            String pattern = "%" + criteria.getClub().toLowerCase() + "%";
            spec = spec.and((root, query, cb) -> 
                cb.or(
                    cb.like(cb.lower(root.get("permanentClub")), pattern),
                    cb.like(cb.lower(root.get("basedClub")), pattern),
                    cb.like(cb.lower(root.get("loanClub")), pattern)
                )
            );
            log.debug("Added club filter: {}", pattern);
        }
        
        // Price range
        if (criteria.getMinPrice() != null) {
            spec = spec.and((root, query, cb) -> 
                cb.greaterThanOrEqualTo(root.get("price"), criteria.getMinPrice())
            );
            log.debug("Added min price filter: {}", criteria.getMinPrice());
        }
        
        if (criteria.getMaxPrice() != null) {
            spec = spec.and((root, query, cb) -> 
                cb.lessThanOrEqualTo(root.get("price"), criteria.getMaxPrice())
            );
            log.debug("Added max price filter: {}", criteria.getMaxPrice());
        }
        
        // Availability status
        if (criteria.getAvailabilityStatus() != null && !criteria.getAvailabilityStatus().trim().isEmpty()) {
            spec = spec.and((root, query, cb) -> 
                cb.equal(root.get("availabilityStatus"), criteria.getAvailabilityStatus())
            );
            log.debug("Added availability status filter: {}", criteria.getAvailabilityStatus());
        }
        
        return spec;
    }
}
