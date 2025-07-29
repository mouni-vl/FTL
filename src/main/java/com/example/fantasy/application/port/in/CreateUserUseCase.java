package com.example.fantasy.application.port.in;

import com.example.fantasy.application.dto.UserDto;

public interface CreateUserUseCase {
    UserDto createUser(UserDto userDto);
}