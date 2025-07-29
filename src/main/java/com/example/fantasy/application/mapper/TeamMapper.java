package com.example.fantasy.application.mapper;

import com.example.fantasy.application.dto.TeamDto;
import com.example.fantasy.core.common.BaseMapper;
import com.example.fantasy.domain.model.Player;
import com.example.fantasy.domain.model.Team;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.ReportingPolicy;

import java.util.stream.Collectors;

/**
 * Mapper for converting between Team entity and TeamDto
 */
@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface TeamMapper extends BaseMapper<Team, TeamDto> {

    /**
     * Convert a Team entity to a TeamDto
     * @param team the team entity
     * @return the team DTO
     */
    @Override
//    @Mapping(source = "owner.positionId", target = "ownerId")
//    @Mapping(source = "league.positionId", target = "leagueId")
//    @Mapping(expression = "java(getPlayerIds(team))", target = "playerIds")
    TeamDto toDto(Team team);

    /**
     * Convert a TeamDto to a Team entity
     * @param teamDto the team DTO
     * @return the team entity
     */
    @Override
//    @Mapping(target = "owner", ignore = true)
//    @Mapping(target = "league", ignore = true)
//    @Mapping(target = "players", ignore = true)
//    @Mapping(target = "createdAt", ignore = true)
//    @Mapping(target = "updatedAt", ignore = true)
//    @Mapping(target = "createdBy", ignore = true)
//    @Mapping(target = "updatedBy", ignore = true)
//    @Mapping(target = "version", ignore = true)
    Team toEntity(TeamDto teamDto);

    /**
     * Update a Team entity from a TeamDto
     * @param teamDto the team DTO with updated values
     * @param team the team entity to update
     * @return the updated team entity
     */
    @Override
//    @Mapping(target = "id", ignore = true)
//    @Mapping(target = "owner", ignore = true)
//    @Mapping(target = "league", ignore = true)
//    @Mapping(target = "players", ignore = true)
//    @Mapping(target = "createdAt", ignore = true)
//    @Mapping(target = "updatedAt", ignore = true)
//    @Mapping(target = "createdBy", ignore = true)
//    @Mapping(target = "updatedBy", ignore = true)
//    @Mapping(target = "version", ignore = true)
    Team updateEntityFromDto(TeamDto teamDto, @MappingTarget Team team);

    /**
     * Get player IDs from a team
     * @param team the team
     * @return the player IDs
     */
    default java.util.List<Long> getPlayerIds(Team team) {
        if (team.getPlayers() == null) {
            return java.util.Collections.emptyList();
        }
        return team.getPlayers().stream()
                .map(Player::getId)
                .collect(Collectors.toList());
    }
}
