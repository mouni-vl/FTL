package com.example.fantasy.adapter.outbound.persistence.entity;

import com.example.fantasy.domain.model.MainPosition;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "player_position")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PlayerPositionEntity {

    @Id
    @Column(name = "player_id")
    private Long playerId;

    @OneToOne
    @MapsId
    @JoinColumn(name = "player_id")
    private PlayerEntity player;

    @Enumerated(EnumType.STRING)
    @Column(name = "main_position", nullable = false)
    private MainPosition mainPosition;

    @Column(name = "gk", nullable = false)
    private Integer gk = 0;

    @Column(name = "dl", nullable = false)
    private Integer dl = 0;

    @Column(name = "dc", nullable = false)
    private Integer dc = 0;

    @Column(name = "dr", nullable = false)
    private Integer dr = 0;

    @Column(name = "wbl", nullable = false)
    private Integer wbl = 0;

    @Column(name = "wbr", nullable = false)
    private Integer wbr = 0;

    @Column(name = "dm", nullable = false)
    private Integer dm = 0;

    @Column(name = "mc", nullable = false)
    private Integer mc = 0;

    @Column(name = "ml", nullable = false)
    private Integer ml = 0;

    @Column(name = "mr", nullable = false)
    private Integer mr = 0;

    @Column(name = "aml", nullable = false)
    private Integer aml = 0;

    @Column(name = "amc", nullable = false)
    private Integer amc = 0;

    @Column(name = "amr", nullable = false)
    private Integer amr = 0;

    @Column(name = "sc", nullable = false)
    private Integer sc = 0;
}