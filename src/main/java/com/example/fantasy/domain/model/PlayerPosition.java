package com.example.fantasy.domain.model;

import lombok.*;

import java.util.HashMap;
import java.util.Map;

/**
 * Value object representing player position capabilities
 */
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class PlayerPosition {
    private Integer gk;
    private Integer dl;
    private Integer dc;
    private Integer dr;
    private Integer wbl;
    private Integer wbr;
    private Integer dm;
    private Integer mc;
    private Integer ml;
    private Integer mr;
    private Integer aml;
    private Integer amc;
    private Integer amr;
    private Integer sc;

    public Map<String, Integer> toMap() {
        Map<String, Integer> map = new HashMap<>();
        if (gk != null && gk > 0) map.put("GK", gk);
        if (dl != null && dl > 0) map.put("DL", dl);
        if (dc != null && dc > 0) map.put("DC", dc);
        if (dr != null && dr > 0) map.put("DR", dr);
        if (wbl != null && wbl > 0) map.put("WBL", wbl);
        if (wbr != null && wbr > 0) map.put("WBR", wbr);
        if (dm != null && dm > 0) map.put("DM", dm);
        if (mc != null && mc > 0) map.put("MC", mc);
        if (ml != null && ml > 0) map.put("ML", ml);
        if (mr != null && mr > 0) map.put("MR", mr);
        if (aml != null && aml > 0) map.put("AML", aml);
        if (amc != null && amc > 0) map.put("AMC", amc);
        if (amr != null && amr > 0) map.put("AMR", amr);
        if (sc != null && sc > 0) map.put("SC", sc);
        return map;
    }

    public static PlayerPosition fromMap(Map<String, Integer> map) {
        if (map == null) return new PlayerPosition();
        
        return PlayerPosition.builder()
                .gk(map.getOrDefault("GK", 0))
                .dl(map.getOrDefault("DL", 0))
                .dc(map.getOrDefault("DC", 0))
                .dr(map.getOrDefault("DR", 0))
                .wbl(map.getOrDefault("WBL", 0))
                .wbr(map.getOrDefault("WBR", 0))
                .dm(map.getOrDefault("DM", 0))
                .mc(map.getOrDefault("MC", 0))
                .ml(map.getOrDefault("ML", 0))
                .mr(map.getOrDefault("MR", 0))
                .aml(map.getOrDefault("AML", 0))
                .amc(map.getOrDefault("AMC", 0))
                .amr(map.getOrDefault("AMR", 0))
                .sc(map.getOrDefault("SC", 0))
                .build();
    }
}
