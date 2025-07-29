package com.example.fantasy.application.port.out;

import com.example.fantasy.domain.model.User;

public interface SaveUserPort {
    User saveUser(User user);
    void deleteUser(Long id);
}
