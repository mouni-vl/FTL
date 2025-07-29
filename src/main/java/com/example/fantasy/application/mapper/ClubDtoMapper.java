package com.example.fantasy.application.mapper;

import com.example.fantasy.application.dto.ClubDto;
import com.example.fantasy.application.dto.light.LightClubDto;
import com.example.fantasy.core.common.transformer.BaseMapstructMapper;
import com.example.fantasy.domain.model.Club;
import org.mapstruct.*;

@Mapper(
    componentModel = "spring",
    uses = {PlayerDtoMapper.class}
)
public interface ClubDtoMapper extends BaseMapstructMapper<Club, ClubDto> {

//    @Mapping(target = "captain", source = "captain", qualifiedByName = "toLight")
//    @Mapping(target = "viceCaptain", source = "viceCaptain", qualifiedByName = "toLight")
//    @Mapping(target = "stadiumId", source = "stadium.id")
    @Override
    ClubDto toSource(Club domain);

//    @Mapping(target = "captain", source = "captain")
//    @Mapping(target = "viceCaptain", source = "viceCaptain")
//    @Mapping(target = "stadium", ignore = true)
//    @Mapping(target = "createdAt", ignore = true)
//    @Mapping(target = "updatedAt", ignore = true)
//    @Mapping(target = "createdBy", ignore = true)
//    @Mapping(target = "updatedBy", ignore = true)
    @Override
    Club toDomain(ClubDto dto);

    @Named("toLight")
//    @Mapping(target = "id", source = "id")
//    @Mapping(target = "fmId", source = "fmId")
//    @Mapping(target = "name", source = "name")
//    @Mapping(target = "shortName", source = "shortName")
//    @Mapping(target = "threeLetterName", source = "threeLetterName")
//    @Mapping(target = "logoUrl", source = "logoUrl")
    LightClubDto toLightDto(Club club);

    @Override
//    @Mapping(target = "captain", ignore = true)
//    @Mapping(target = "viceCaptain", ignore = true)
//    @Mapping(target = "stadium", ignore = true)
//    @Mapping(target = "createdAt", ignore = true)
//    @Mapping(target = "updatedAt", ignore = true)
//    @Mapping(target = "createdBy", ignore = true)
//    @Mapping(target = "updatedBy", ignore = true)
    Club updateDomain(@MappingTarget Club target, ClubDto source);

    @AfterMapping
    default void afterMapping(@MappingTarget Club club, ClubDto dto) {
        // Additional mapping logic can be added here if needed
    }
}
