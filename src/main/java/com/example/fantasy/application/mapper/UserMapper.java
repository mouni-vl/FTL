package com.example.fantasy.application.mapper;

import com.example.fantasy.application.dto.UserDto;
import com.example.fantasy.core.common.transformer.BaseMapstructMapper;
import com.example.fantasy.domain.model.User;
import org.mapstruct.*;

/**
 * MapStruct mapper for transforming between User domain entities and UserDto objects.
 * Extends our BaseMapstructMapper to integrate with our transformer architecture.
 */
@Mapper(
    componentModel = "spring",
    unmappedTargetPolicy = ReportingPolicy.IGNORE,
    nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE
)
public interface UserMapper extends BaseMapstructMapper<User, UserDto> {

    /**
     * Maps a User domain entity to a UserDto.
     * MapStruct will generate the implementation of this method.
     *
     * @param user The user entity to transform
     * @return The resulting UserDto
     */
    @Override
    @Mapping(target = "password", ignore = true)  // Don't map passwords to DTOs for security
    UserDto toSource(User user);

    /**
     * Maps a UserDto to a User domain entity.
     * MapStruct will generate the implementation of this method.
     *
     * @param userDto The DTO to transform
     * @return The resulting User entity
     */
    @Override
    @Mapping(target = "passwordHash", source = "password")  // Map password to passwordHash
    User toDomain(UserDto userDto);

    /**
     * Updates a User domain entity with data from a UserDto.
     * MapStruct will generate the implementation of this method.
     *
     * @param user The user entity to update
     * @param userDto The DTO containing the new data
     * @return The updated User entity
     */
    @Override
    @Mapping(target = "passwordHash", source = "password", conditionExpression = "java(userDto.getPassword() != null && !userDto.getPassword().isEmpty())")
    User updateDomain(@MappingTarget User user, UserDto userDto);

    /**
     * Custom post-processing after mapping to a domain entity.
     * This demonstrates how to extend the mapping with custom logic.
     *
     * @param user The mapped user entity
     * @param userDto The source DTO
     * @return The processed user entity
     */
    @Override
    default User postProcessDomain(User user, UserDto userDto) {
        // Example of custom post-processing logic
        if (user.getRole() == null) {
            // Set default role if not specified
            user.setRole(com.example.fantasy.domain.model.Role.USER);
        }
        return user;
    }
}
//import com.example.fantasy.application.dto.UserDto;
//import com.example.fantasy.domain.model.User;
//import org.mapstruct.Mapper;
//import org.mapstruct.Mapping;
//import org.mapstruct.MappingTarget;
//import org.mapstruct.factory.Mappers;
//
//@Mapper(componentModel = "spring")
//public interface UserMapper {
//    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);
//
//    @Mapping(target = "passwordHash", ignore = true)
//    User toEntity(UserDto dto);
//
//    @Mapping(target = "password", ignore = true)
//    UserDto toDto(User entity);
//
//    @Mapping(target = "positionId", ignore = true)
//    @Mapping(target = "passwordHash", ignore = true)
//    @Mapping(target = "createdAt", ignore = true)
//    void updateEntityFromDto(UserDto dto, @MappingTarget User entity);
//}
