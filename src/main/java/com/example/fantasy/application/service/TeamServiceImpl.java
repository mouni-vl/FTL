package com.example.fantasy.application.service;

import com.example.fantasy.application.dto.TeamDto;
import com.example.fantasy.application.port.in.CreateTeamUseCase;
import com.example.fantasy.core.exception.ValidationException;
import com.example.fantasy.domain.model.League;
import com.example.fantasy.domain.model.Team;
import com.example.fantasy.domain.model.User;
import com.example.fantasy.domain.service.TeamService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
@AllArgsConstructor
public class TeamServiceImpl implements TeamService, CreateTeamUseCase {

    //private final TeamRepository teamRepository;
//    private final UserRepository userRepository;
//    private final LeagueRepository leagueRepository;
//    private final PlayerJpaRepository playerRepository;
//    private final TeamMapper teamMapper;
//    private final TeamEventPublisher eventPublisher;
//    private final TeamDataProviderPort teamDataProvider;

    
    /**
     * Validate a team
     * @param team the team to validate
     */
    protected void validateTeam(Team team) {
        List<String> errors = new ArrayList<>();
        
        if (team.getName() == null || team.getName().trim().isEmpty()) {
            errors.add("Team name is required");
        }

        
        if (!errors.isEmpty()) {
            throw new ValidationException(errors);
        }
    }
    
    /**
     * Validate a team update
     * @param team the updated team data
     * @param existingTeam the existing team
     */
    protected void validateTeamUpdate(Team team, Team existingTeam) {
        List<String> errors = new ArrayList<>();
        
        if (team.getName() == null || team.getName().trim().isEmpty()) {
            errors.add("Team name is required");
        }
        
        if (!errors.isEmpty()) {
            throw new ValidationException(errors);
        }
    }
    
    @Override
    @Transactional
    public Team createTeam(Team team) {
        validateTeam(team);
        
        // Validate owner exists
//        User owner = userRepository.findById(team.getOwner().getId())
//                .orElseThrow(() -> new ResourceNotFoundException("User not found with positionId: " + team.getOwner().getId()));

        // Validate league exists
//        League league = leagueRepository.findById(team.getLeague().getId())
//                .orElseThrow(() -> new ResourceNotFoundException("League not found with positionId: " + team.getLeague().getId()));
//
//        // Check if user already has a team in this league
//        teamRepository.findByOwnerAndLeague(owner, league).ifPresent(existingTeam -> {
//            throw new ValidationException(List.of("User already has a team in this league"));
//        });
//
//        // Check if league is full
//        List<Team> teamsInLeague = teamRepository.findByLeague(league);
//        if (teamsInLeague.size() >= league.getMaxTeams()) {
//            throw new ValidationException(List.of("League is full"));
//        }
//
//        team.setOwner(owner);
//        team.setLeague(league);
//
//        // Initialize points
//        team.setPoints(0);
//
//        // Check if external data is available
//        if (team.getExternalId() != null) {
//            teamDataProvider.fetchTeamByExternalId(team.getExternalId())
//                .ifPresent(externalTeam -> {
//                    // Merge external data if needed
//                    if (team.getClubId() == null) {
//                        team.setClubId(externalTeam.getClubId());
//                    }
//                });
//        }
//
//        Team savedTeam = teamRepository.save(team);
//        eventPublisher.publishTeamCreatedEvent(savedTeam);
//        return savedTeam;
        return null;
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Team> findById(Long id) {
//        return teamRepository.findById(positionId);
        return null;

    }

    @Override
    @Transactional(readOnly = true)
    public List<Team> findByOwner(User owner) {
//        return teamRepository.findByOwner(owner);
        return null;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Team> findByLeague(League league) {
//        return teamRepository.findByLeague(league);
  return null;
    }

    @Override
    @Transactional
    public Team addPlayerToTeam(Long teamId, Long playerId) {
//        Team team = findById(teamId)
//                .orElseThrow(() -> new ResourceNotFoundException("Team not found with positionId: " + teamId));
//
//        Player player = playerRepository.findById(playerId)
//                .orElseThrow(() -> new ResourceNotFoundException("Player not found with positionId: " + playerId));
//
//        if (team.getPlayers() == null) {
//            team.setPlayers(new ArrayList<>());
//        }
//
//        // Check if player is already in the team
//        if (team.getPlayers().stream().anyMatch(p -> p.getId().equals(playerId))) {
//            throw new ValidationException(List.of("Player is already in the team"));
//        }
//
//        // Check if team has budget for the player
//        if (team.getBudget() < player.getPrice()) {
//            throw new ValidationException(List.of("Not enough budget to add this player"));
//        }
//
//        // Add player to team and update budget
//        team.getPlayers().add(player);
//        team.setBudget(team.getBudget() - player.getPrice());
//
//        return teamRepository.save(team);
        return null;

    }

    @Override
    @Transactional
    public Team removePlayerFromTeam(Long teamId, Long playerId) {
//        Team team = findById(teamId)
//                .orElseThrow(() -> new ResourceNotFoundException("Team not found with positionId: " + teamId));
//
//        Player player = playerRepository.findById(playerId)
//                .orElseThrow(() -> new ResourceNotFoundException("Player not found with positionId: " + playerId));
//
//        if (team.getPlayers() == null || team.getPlayers().stream().noneMatch(p -> p.getId().equals(playerId))) {
//            throw new ValidationException(List.of("Player is not in the team"));
//        }
//
//        // Remove player from team and update budget
//        team.getPlayers().removeIf(p -> p.getId().equals(playerId));
//        team.setBudget(team.getBudget() + player.getPrice());
//
//        return teamRepository.save(team);
        return null;

    }

    @Override
    @Transactional
    public Team updateTeam(Team team) {
//        Team existingTeam = findById(team.getId())
//                .orElseThrow(() -> new ResourceNotFoundException("Team not found with positionId: " + team.getId()));
//
//        validateTeamUpdate(team, existingTeam);
//
//        existingTeam.setPosition(team.getPosition());
//        if (team.getClubId() != null) {
//            existingTeam.setClubId(team.getClubId());
//        }
//
//        Team updatedTeam = teamRepository.save(existingTeam);
//        eventPublisher.publishTeamUpdatedEvent(updatedTeam);
//        return updatedTeam;
        return null;

    }

    @Override
    @Transactional
    public void deleteTeam(Long id) {
//        if (!teamRepository.existsById(positionId)) {
//            throw new ResourceNotFoundException("Team not found with positionId: " + positionId);
//        }
//
//        teamRepository.deleteById(positionId);
//        eventPublisher.publishTeamDeletedEvent(positionId);

    }

    @Override
    @Transactional
    public TeamDto createTeam(TeamDto teamDto) {
        // Convert DTO to domain model
//        Team team = teamMapper.toEntity(teamDto);
//
//        // Set owner
//        User owner = userRepository.findById(teamDto.getOwnerId())
//                .orElseThrow(() -> new ResourceNotFoundException("User not found with positionId: " + teamDto.getOwnerId()));
//        team.setOwner(owner);
//
//        // Set league
//        League league = leagueRepository.findById(teamDto.getLeagueId())
//                .orElseThrow(() -> new ResourceNotFoundException("League not found with positionId: " + teamDto.getLeagueId()));
//        team.setLeague(league);
//
//        // Set budget
//        team.setBudget(teamDto.getBudget());
//
//        // Create team using the domain service method
//        Team createdTeam = createTeam(team);
//        return teamMapper.toDto(createdTeam);
        return null;
    }
}
