package com.example.fantasy.adapter.outbound.persistence.mapper;

import com.example.fantasy.adapter.outbound.persistence.entity.ClubEntity;
import com.example.fantasy.adapter.outbound.persistence.entity.PlayerEntity;
import com.example.fantasy.adapter.outbound.persistence.entity.StadiumEntity;
import com.example.fantasy.adapter.outbound.persistence.repository.PlayerJpaRepository;
import com.example.fantasy.adapter.outbound.persistence.repository.StadiumJpaRepository;
import com.example.fantasy.core.common.transformer.BaseMapstructMapper;
import com.example.fantasy.domain.model.Club;
import org.mapstruct.*;
import org.springframework.beans.factory.annotation.Autowired;

@Mapper(
    componentModel = "spring",
    uses = {PlayerEntityMapper.class, StadiumEntityMapper.class},
    imports = {java.time.Instant.class}
)
public abstract class ClubEntityMapper implements BaseMapstructMapper<Club, ClubEntity> {

    @Autowired
    private PlayerJpaRepository playerRepository;
    
    @Autowired
    private StadiumJpaRepository stadiumRepository;

    @Mapping(target = "captain", source = "captain")
    @Mapping(target = "viceCaptain", source = "viceCaptain")
    @Mapping(target = "stadium", source = "stadium")
    @Override
    public abstract Club toDomain(ClubEntity entity);

    @Mapping(target = "captain", source = "captain")
    @Mapping(target = "viceCaptain", source = "viceCaptain")
    @Mapping(target = "stadium", source = "stadium")
    @Override
    public abstract ClubEntity toSource(Club domain);

    @AfterMapping
    protected void linkEntities(@MappingTarget ClubEntity entity) {
        // Set bidirectional references if needed
        if (entity.getStadium() != null) {
            entity.getStadium().getClubs().add(entity);
        }
    }

    /**
     * Custom mapping method to merge an existing entity with updated domain values
     */
    public ClubEntity mergeToEntity(ClubEntity existingEntity, Club updatedDomain) {
        if (updatedDomain == null) {
            return existingEntity;
        }

        // Update scalar properties
        existingEntity.setName(updatedDomain.getName());
        existingEntity.setShortName(updatedDomain.getShortName());
        existingEntity.setThreeLetterName(updatedDomain.getThreeLetterName());
        existingEntity.setLogoUrl(updatedDomain.getLogoUrl());
        existingEntity.setYearFounded(updatedDomain.getYearFounded());
        existingEntity.setFootballingNation(updatedDomain.getFootballingNation());
        existingEntity.setReputation(updatedDomain.getReputation());
        existingEntity.setLikelyFinishingGroup(updatedDomain.getLikelyFinishingGroup());
        existingEntity.setCa16(updatedDomain.getCa16());
        existingEntity.setOfficialHashtag(updatedDomain.getOfficialHashtag());
        existingEntity.setNickname(updatedDomain.getNickname());

        // Update relationships with proper error handling
//        if (updatedDomain.getCaptain() != null && updatedDomain.getCaptain().getId() != null) {
//            playerRepository.findById(updatedDomain.getCaptain().getId())
//                .ifPresent(existingEntity::setCaptain);
//        } else {
//            existingEntity.setCaptain(null);
//        }
//
//        if (updatedDomain.getViceCaptain() != null && updatedDomain.getViceCaptain().getId() != null) {
//            playerRepository.findById(updatedDomain.getViceCaptain().getId())
//                .ifPresent(existingEntity::setViceCaptain);
//        } else {
//            existingEntity.setViceCaptain(null);
//        }

//        if (updatedDomain.getStadium() != null && updatedDomain.getStadium().getId() != null) {
//            stadiumRepository.findById(updatedDomain.getStadium().getId())
//                .ifPresent(stadium -> {
//                    // Remove from previous stadium's clubs collection if exists
//                    if (existingEntity.getStadium() != null) {
//                        existingEntity.getStadium().getClubs().remove(existingEntity);
//                    }
//
//                    // Set new stadium and add to its clubs collection
//                    existingEntity.setStadium(stadium);
//                    stadium.getClubs().add(existingEntity);
//                });
//        } else {
//            // Remove from stadium's clubs collection if exists
//            if (existingEntity.getStadium() != null) {
//                existingEntity.getStadium().getClubs().remove(existingEntity);
//            }
//            existingEntity.setStadium(null);
//        }

        return existingEntity;
    }
}