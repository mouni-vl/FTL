package com.example.fantasy.adapter.outbound.persistence.entity;

import com.example.fantasy.core.persistence.domain.BaseEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;


@Entity
@Table(
        name = "season_stats",
        uniqueConstraints = @UniqueConstraint(
                name = "uk_season_stats_player_season",
                columnNames = { "player_id", "season" }
        )
)
public class SeasonStatsEntity extends BaseEntity {
}
