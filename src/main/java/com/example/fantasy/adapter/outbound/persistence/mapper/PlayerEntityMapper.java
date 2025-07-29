package com.example.fantasy.adapter.outbound.persistence.mapper;

import com.example.fantasy.adapter.outbound.persistence.entity.PlayerEntity;
import com.example.fantasy.adapter.outbound.persistence.entity.PlayerPositionEntity;
import com.example.fantasy.core.common.transformer.BaseMapstructMapper;
import com.example.fantasy.domain.model.Player;
import org.mapstruct.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.HashMap;
import java.util.Map;

@Mapper(
    componentModel = MappingConstants.ComponentModel.SPRING,
    unmappedTargetPolicy = ReportingPolicy.IGNORE
)
public interface PlayerEntityMapper extends BaseMapstructMapper<Player, PlayerEntity> {

    Logger log = LoggerFactory.getLogger(PlayerEntityMapper.class);

    @Mapping(target = "positions", expression = "java(mapPositionsToMap(entity.getPositions()))")
    @Override
    Player toDomain(PlayerEntity entity);

    @Mapping(target = "positions", ignore = true) // We'll handle this in the after mapping
    @Override
    PlayerEntity toSource(Player domain);
    
    /**
     * Merges a domain player into an existing entity for update operations
     * This preserves the entity's identity while updating its fields
     * 
     * @param existingEntity The existing entity to update
     * @param player The domain player with updated values
     * @return The updated entity (same instance as existingEntity)
     */
    default PlayerEntity mergeToEntity(PlayerEntity existingEntity, Player player) {
        // Update all fields except ID (and possibly audit fields if they should be auto-updated)
        existingEntity.setFmId(player.getFmId());
        existingEntity.setFirstName(player.getFirstName());
        existingEntity.setSecondName(player.getSecondName());
        existingEntity.setFullName(player.getFullName());
        existingEntity.setDateOfBirth(player.getDateOfBirth());
        existingEntity.setCityOfBirth(player.getCityOfBirth());
        existingEntity.setPermanentClub(player.getPermanentClub());
        existingEntity.setBasedClub(player.getBasedClub());
        existingEntity.setLoanClub(player.getLoanClub());
        existingEntity.setSquadNumber(player.getSquadNumber());
        existingEntity.setNationality1(player.getNationality1());
        existingEntity.setNationality2(player.getNationality2());
        existingEntity.setNationality3(player.getNationality3());
        existingEntity.setNfe(player.getNfe());
        existingEntity.setCurrentAbility(player.getCurrentAbility());
        existingEntity.setPotentialAbility(player.getPotentialAbility());
        existingEntity.setLeftFoot(player.getLeftFoot());
        existingEntity.setRightFoot(player.getRightFoot());
        existingEntity.setAvailabilityStatus(player.getAvailabilityStatus());
        existingEntity.setMainPosition(player.getMainPosition());

        // Handle the positions relationship
        updateEntityPositions(existingEntity, player);

        existingEntity.setUpdatedAt(Instant.now());
        
        return existingEntity;
    }
    
    // Helper method to extract positions from entity to map
    default Map<String, Integer> mapPositionsToMap(PlayerPositionEntity positionEntity) {
        if (positionEntity == null) {
            return new HashMap<>();
        }
        
        Map<String, Integer> positions = new HashMap<>();
        if (positionEntity.getGk() > 0) positions.put("GK", positionEntity.getGk());
        if (positionEntity.getDl() > 0) positions.put("DL", positionEntity.getDl());
        if (positionEntity.getDc() > 0) positions.put("DC", positionEntity.getDc());
        if (positionEntity.getDr() > 0) positions.put("DR", positionEntity.getDr());
        if (positionEntity.getWbl() > 0) positions.put("WBL", positionEntity.getWbl());
        if (positionEntity.getWbr() > 0) positions.put("WBR", positionEntity.getWbr());
        if (positionEntity.getDm() > 0) positions.put("DM", positionEntity.getDm());
        if (positionEntity.getMc() > 0) positions.put("MC", positionEntity.getMc());
        if (positionEntity.getMl() > 0) positions.put("ML", positionEntity.getMl());
        if (positionEntity.getMr() > 0) positions.put("MR", positionEntity.getMr());
        if (positionEntity.getAml() > 0) positions.put("AML", positionEntity.getAml());
        if (positionEntity.getAmc() > 0) positions.put("AMC", positionEntity.getAmc());
        if (positionEntity.getAmr() > 0) positions.put("AMR", positionEntity.getAmr());
        if (positionEntity.getSc() > 0) positions.put("SC", positionEntity.getSc());
        
        return positions;
    }
    
    /**
     * Updates the positions for an existing entity
     */
    default void updateEntityPositions(PlayerEntity existingEntity, Player player) {
        if (player.getPositions() == null || player.getPositions().isEmpty()) {
            // If player has no positions now, remove any existing positions
            if (existingEntity.getPositions() != null) {
                existingEntity.getPositions().setPlayer(null);
                existingEntity.setPositions(null);
            }
            return;
        }

        // Get existing positions or create new
        PlayerPositionEntity posEntity = existingEntity.getPositions();
        if (posEntity == null) {
            log.debug("Creating new PlayerPositionEntity for player with ID: {}", existingEntity.getId());
            posEntity = new PlayerPositionEntity();
            posEntity.setPlayer(existingEntity);
            existingEntity.setPositions(posEntity);
        } else {
            log.debug("Updating existing PlayerPositionEntity for player with ID: {}", existingEntity.getId());
        }

        // Update positions
        Map<String, Integer> positions = player.getPositions();
        posEntity.setGk(positions.getOrDefault("GK", 0));
        posEntity.setDl(positions.getOrDefault("DL", 0));
        posEntity.setDc(positions.getOrDefault("DC", 0));
        posEntity.setDr(positions.getOrDefault("DR", 0));
        posEntity.setWbl(positions.getOrDefault("WBL", 0));
        posEntity.setWbr(positions.getOrDefault("WBR", 0));
        posEntity.setDm(positions.getOrDefault("DM", 0));
        posEntity.setMc(positions.getOrDefault("MC", 0));
        posEntity.setMl(positions.getOrDefault("ML", 0));
        posEntity.setMr(positions.getOrDefault("MR", 0));
        posEntity.setAml(positions.getOrDefault("AML", 0));
        posEntity.setAmc(positions.getOrDefault("AMC", 0));
        posEntity.setAmr(positions.getOrDefault("AMR", 0));
        posEntity.setSc(positions.getOrDefault("SC", 0));
        
        posEntity.setMainPosition(player.getMainPosition());
    }
    
    // After mapping method to ensure proper bidirectional relationship for new entities
    @AfterMapping
    default void setPlayerPositions(@MappingTarget PlayerEntity playerEntity, Player player) {
        if (player != null && player.getPositions() != null && !player.getPositions().isEmpty()) {
            updateEntityPositions(playerEntity, player);
        }
    }

    // Date conversion methods (if needed for other fields)
    default LocalDate map(Instant instant) {
        return instant != null ? LocalDate.ofInstant(instant, ZoneId.systemDefault()) : null;
    }

    default Instant map(LocalDate localDate) {
        return localDate != null ? localDate.atStartOfDay(ZoneId.systemDefault()).toInstant() : null;
    }
}
