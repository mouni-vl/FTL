package com.example.fantasy.adapter.outbound.persistence.mapper;

import com.example.fantasy.adapter.outbound.persistence.entity.StadiumEntity;
import com.example.fantasy.core.common.transformer.BaseMapstructMapper;
import com.example.fantasy.domain.model.Stadium;
import org.mapstruct.*;

@Mapper(
    componentModel = "spring"
)
public interface StadiumEntityMapper extends BaseMapstructMapper<Stadium, StadiumEntity> {

    @Mapping(target = "clubs", ignore = true)
    @Override
    StadiumEntity toSource(Stadium domain);

    @Override
    Stadium toDomain(StadiumEntity entity);

//    @Mapping(target = "clubs", ignore = true)
//    @Override
//    StadiumEntity updateSource(@MappingTarget StadiumEntity target, Stadium source);

    @Override
    Stadium updateDomain(@MappingTarget Stadium target, StadiumEntity source);
}
