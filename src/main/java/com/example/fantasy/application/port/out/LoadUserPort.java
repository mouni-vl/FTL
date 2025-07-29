package com.example.fantasy.application.port.out;

import com.example.fantasy.domain.model.User;
import java.util.Optional;
import java.util.List;

public interface LoadUserPort {
    Optional<User> loadUserById(Long id);
    Optional<User> loadUserByEmail(String email);
    List<User> loadAllUsers();
}
