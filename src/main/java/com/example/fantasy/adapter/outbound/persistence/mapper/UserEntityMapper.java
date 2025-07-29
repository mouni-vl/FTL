package com.example.fantasy.adapter.outbound.persistence.mapper;

import com.example.fantasy.adapter.outbound.persistence.entity.UserEntity;
import com.example.fantasy.domain.model.Role;
import com.example.fantasy.domain.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface UserEntityMapper {
    UserEntityMapper INSTANCE = Mappers.getMapper(UserEntityMapper.class);

    @Mapping(source = "passwordHash", target = "passwordHash")
    @Mapping(source = "displayName", target = "displayName")
    @Mapping(target = "role", expression = "java(mapToRoleString(domain.getRole()))")
    UserEntity toEntity(User domain);

    @Mapping(source = "passwordHash", target = "passwordHash")
    @Mapping(source = "displayName", target = "displayName")
//    @Mapping(target = "role", expression = "java(mapToRoleEnum(entity.getRole()))")
    User toDomain(UserEntity entity);

    default Role mapToRoleString(Role role) {
        return null;
//        return role != null ? role.name() : null;
    }

    default Role mapToRoleEnum(String role) {
        return null;
//        return role != null ? Role.valueOf(role) : null;
    }
}
