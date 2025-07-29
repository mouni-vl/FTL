package com.example.fantasy.adapter.outbound.persistence.entity;

import com.example.fantasy.core.persistence.domain.BaseEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;

@Entity
@Table(
        name = "gameweek_stats",
        uniqueConstraints = @UniqueConstraint(
                name = "uk_gameweek_stats_player_season_round",
                columnNames = { "player_id", "season", "round" }
        )
)
public class GameweekStatsEntity extends BaseEntity {
}
