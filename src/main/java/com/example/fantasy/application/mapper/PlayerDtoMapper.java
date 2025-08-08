package com.example.fantasy.application.mapper;

import com.example.fantasy.application.dto.PlayerDto;
import com.example.fantasy.core.common.transformer.BaseMapstructMapper;
import com.example.fantasy.domain.model.Player;
import org.mapstruct.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.LinkedHashMap;

@Mapper(
        componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE
)
public interface PlayerDtoMapper extends BaseMapstructMapper<Player, PlayerDto> {

    Logger log = LoggerFactory.getLogger(PlayerDtoMapper.class);

    @Override
    PlayerDto toSource(Player domain);

    @Override
    Player toDomain(PlayerDto dto);

    @Override
    default Player updateDomain(@MappingTarget Player domain, PlayerDto dto) {
        if (dto == null) {
            return domain;
        }

        log.debug("Updating domain object with DTO. Domain ID: {}", domain.getId());
        
        // Update fields from DTO (only if not null)
        //if (dto.getFmId() != null) domain.setFmId(dto.getFmId());
        if (dto.getFirstName() != null) domain.setFirstName(dto.getFirstName());
        if (dto.getSecondName() != null) domain.setSecondName(dto.getSecondName());
        if (dto.getFullName() != null) domain.setFullName(dto.getFullName());
        if (dto.getDateOfBirth() != null) domain.setDateOfBirth(dto.getDateOfBirth());
        if (dto.getCityOfBirth() != null) domain.setCityOfBirth(dto.getCityOfBirth());
        if (dto.getYearOfBirth() != null) domain.setYearOfBirth(dto.getYearOfBirth());
        if (dto.getPermanentClub() != null) domain.setPermanentClub(dto.getPermanentClub());
        if (dto.getBasedClub() != null) domain.setBasedClub(dto.getBasedClub());
        if (dto.getLoanClub() != null) domain.setLoanClub(dto.getLoanClub());
        if (dto.getSquadNumber() != null) domain.setSquadNumber(dto.getSquadNumber());
        if (dto.getNationality1() != null) domain.setNationality1(dto.getNationality1());
        if (dto.getNationality2() != null) domain.setNationality2(dto.getNationality2());
        if (dto.getNationality3() != null) domain.setNationality3(dto.getNationality3());
        if (dto.getNfe() != null) domain.setNfe(dto.getNfe());
        if (dto.getCurrentAbility() != null) domain.setCurrentAbility(dto.getCurrentAbility());
        if (dto.getPotentialAbility() != null) domain.setPotentialAbility(dto.getPotentialAbility());
        if (dto.getLeftFoot() != null) domain.setLeftFoot(dto.getLeftFoot());
        if (dto.getRightFoot() != null) domain.setRightFoot(dto.getRightFoot());
        if (dto.getAvailabilityStatus() != null) domain.setAvailabilityStatus(dto.getAvailabilityStatus());
        if (dto.getMainPosition() != null) domain.setMainPosition(dto.getMainPosition());
        if (dto.getPositions() != null) domain.setPositions(new LinkedHashMap<>(dto.getPositions()));
        if (dto.getPrice() != null) domain.setPrice(dto.getPrice());
        if (dto.getTotalPoints() != null) domain.setTotalPoints(dto.getTotalPoints());
        if (dto.getPhotoUrl() != null) domain.setPhotoUrl(dto.getPhotoUrl());
        if (dto.getActive() != null) domain.setActive(dto.getActive());
        
        return domain;
    }
}
