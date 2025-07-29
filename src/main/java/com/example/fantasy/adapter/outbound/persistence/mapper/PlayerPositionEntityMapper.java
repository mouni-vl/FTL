package com.example.fantasy.adapter.outbound.persistence.mapper;

import com.example.fantasy.adapter.outbound.persistence.entity.PlayerPositionEntity;
import com.example.fantasy.core.common.transformer.BaseMapstructMapper;
import com.example.fantasy.domain.model.PlayerPosition;
import org.mapstruct.*;

import java.util.HashMap;
import java.util.Map;

@Mapper(
    componentModel = MappingConstants.ComponentModel.SPRING,
    unmappedTargetPolicy = ReportingPolicy.IGNORE
)
public interface PlayerPositionEntityMapper extends BaseMapstructMapper<PlayerPosition, PlayerPositionEntity> {
    
    @Mapping(target = "playerId", ignore = true)
    @Mapping(target = "player", ignore = true)
    @Mapping(target = "mainPosition", ignore = true) // This is already in Player, avoid duplication
    @Override
    PlayerPositionEntity toSource(PlayerPosition domain);

    @Override
    PlayerPosition toDomain(PlayerPositionEntity entity);
    
    /**
     * Maps a PlayerPosition entity to a Map<String, Integer> representation
     */
    default Map<String, Integer> toPositionsMap(PlayerPositionEntity entity) {
        if (entity == null) {
            return new HashMap<>();
        }
        
        Map<String, Integer> positions = new HashMap<>();
        if (entity.getGk() > 0) positions.put("GK", entity.getGk());
        if (entity.getDl() > 0) positions.put("DL", entity.getDl());
        if (entity.getDc() > 0) positions.put("DC", entity.getDc());
        if (entity.getDr() > 0) positions.put("DR", entity.getDr());
        if (entity.getWbl() > 0) positions.put("WBL", entity.getWbl());
        if (entity.getWbr() > 0) positions.put("WBR", entity.getWbr());
        if (entity.getDm() > 0) positions.put("DM", entity.getDm());
        if (entity.getMc() > 0) positions.put("MC", entity.getMc());
        if (entity.getMl() > 0) positions.put("ML", entity.getMl());
        if (entity.getMr() > 0) positions.put("MR", entity.getMr());
        if (entity.getAml() > 0) positions.put("AML", entity.getAml());
        if (entity.getAmc() > 0) positions.put("AMC", entity.getAmc());
        if (entity.getAmr() > 0) positions.put("AMR", entity.getAmr());
        if (entity.getSc() > 0) positions.put("SC", entity.getSc());
        
        return positions;
    }
    
    /**
     * Creates a PlayerPositionEntity from a Map<String, Integer>
     */
    default PlayerPositionEntity fromPositionsMap(Map<String, Integer> positions) {
        if (positions == null || positions.isEmpty()) {
            return null;
        }
        
        PlayerPositionEntity entity = new PlayerPositionEntity();
        entity.setGk(positions.getOrDefault("GK", 0));
        entity.setDl(positions.getOrDefault("DL", 0));
        entity.setDc(positions.getOrDefault("DC", 0));
        entity.setDr(positions.getOrDefault("DR", 0));
        entity.setWbl(positions.getOrDefault("WBL", 0));
        entity.setWbr(positions.getOrDefault("WBR", 0));
        entity.setDm(positions.getOrDefault("DM", 0));
        entity.setMc(positions.getOrDefault("MC", 0));
        entity.setMl(positions.getOrDefault("ML", 0));
        entity.setMr(positions.getOrDefault("MR", 0));
        entity.setAml(positions.getOrDefault("AML", 0));
        entity.setAmc(positions.getOrDefault("AMC", 0));
        entity.setAmr(positions.getOrDefault("AMR", 0));
        entity.setSc(positions.getOrDefault("SC", 0));
        
        return entity;
    }
}
