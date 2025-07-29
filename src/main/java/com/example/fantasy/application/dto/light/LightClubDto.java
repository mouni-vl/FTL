package com.example.fantasy.application.dto.light;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LightClubDto {
    private Long id;
    private Integer fmId;
    private String name;
    private String shortName;
    private String threeLetterName;
    private String logoUrl;
}
