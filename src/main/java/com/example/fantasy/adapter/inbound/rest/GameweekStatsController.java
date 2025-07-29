package com.example.fantasy.adapter.inbound.rest;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/players/{playerId}/seasons/{season}/gameweeks")
public class GameweekStatsController {
}
