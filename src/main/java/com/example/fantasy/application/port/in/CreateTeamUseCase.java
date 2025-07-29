package com.example.fantasy.application.port.in;

import com.example.fantasy.application.dto.TeamDto;

public interface CreateTeamUseCase {
    TeamDto createTeam(TeamDto teamDto);
}
