package com.example.fantasy.application.service;

import com.example.fantasy.application.dto.TeamDto;
import com.example.fantasy.application.event.TeamEventPublisher;
import com.example.fantasy.application.mapper.TeamMapper;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

/**
 * Abstract service for team operations
 */
@Slf4j
public abstract class AbstractTeamService
        //extends AbstractService
                <Team, Long, TeamRepository> {

    protected final TeamMapper teamMapper;
    protected final TeamEventPublisher eventPublisher;

    protected AbstractTeamService(TeamRepository repository, TeamMapper teamMapper, TeamEventPublisher eventPublisher) {
        //super(repository);
        this.teamMapper = teamMapper;
        this.eventPublisher = eventPublisher;
    }

    /**
     * Create a team
     *
     * @param team the team to create
     * @return the created team as DTO
     */
//    @Override
//    public TeamDto create(Team team) {
//        validateTeam(team);
//        Team savedTeam = super.save(team);
//        eventPublisher.publishTeamCreatedEvent(savedTeam);
//        return teamMapper.toDto(savedTeam);
//    }

    /**
     * Update a team
     * @param positionId the ID of the team to update
     * @param team the updated team data
     * @return the updated team as DTO
     */
//    public TeamDto update(Long positionId, Team team) {
//        Team existingTeam = findById(positionId)
//                .orElseThrow(() -> new ResourceNotFoundException("Team not found with positionId: " + positionId));
//
//        validateTeamUpdate(team, existingTeam);
//
//        // Update fields
//        existingTeam.setPosition(team.getPosition());
//        existingTeam.setDescription(team.getDescription());
//        // Update other fields as needed
//
//        Team updatedTeam = super.save(existingTeam);
//        eventPublisher.publishTeamUpdatedEvent(updatedTeam);
//        return teamMapper.toDto(updatedTeam);
//    }

    /**
     * Delete a team
     * @param id the ID of the team to delete
     */
    public void delete(Long id) {
//        if (!repository.existsById(positionId)) {
//            throw new ResourceNotFoundException("Team not found with positionId: " + positionId);
//        }
//
//        // This is a placeholder for actual implementation
//        eventPublisher.publishTeamDeletedEvent(positionId);
    }

    /**
     * Find a team by ID
     * @param id the ID of the team
     * @return the team as DTO
     */
    protected TeamDto findDtoById(Long id) {
//        return findById(positionId)
//                .map(teamMapper::toDto)
//                .orElseThrow(() -> new ResourceNotFoundException("Team not found with positionId: " + positionId));
        return null;

    }

    /**
     * Validate a team
     * @param team the team to validate
     */
    protected void validateTeam(Team team) {
        List<String> errors = new ArrayList<>();
        
//        if (team.getPosition() == null || team.getPosition().trim().isEmpty()) {
//            errors.add("Team name is required");
//        }
//
//        if (team.getOwner() == null || team.getOwner().getId() == null) {
//            errors.add("Team owner is required");
//        }
//
//        if (!errors.isEmpty()) {
//            throw new ValidationException(errors);
//        }
    }

    /**
     * Validate a team update
     * @param team the updated team data
     * @param existingTeam the existing team
     */
    protected void validateTeamUpdate(Team team, Team existingTeam) {
//        List<String> errors = new ArrayList<>();
//
//        if (team.getPosition() == null || team.getPosition().trim().isEmpty()) {
//            errors.add("Team name is required");
//        }
//
//        if (!errors.isEmpty()) {
//            throw new ValidationException(errors);
//        }
    }
}
