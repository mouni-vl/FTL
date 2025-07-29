package com.example.fantasy.application.service;

import com.example.fantasy.core.common.FunctionalValidator;
import com.example.fantasy.core.exception.ApplicationException;
import com.example.fantasy.domain.model.User;
import com.example.fantasy.application.port.out.UserRepository;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public abstract class AbstractUserService
        //extends AbstractService<User, Long, BaseRepository<Team, Long>>
{
    
    private final UserRepository userRepository;
    
    protected AbstractUserService(UserRepository userRepository) {
        //super(userRepository);
        this.userRepository = userRepository;
    }
    
    //@Override
    protected void validateOnCreate(User user) {
        // Example validator chain
        FunctionalValidator<User> emailNotInUse = u -> {
            if (userRepository.findByEmail(u.getEmail()).isPresent()) {
                throw new ApplicationException("Email already in use");
            }
        };
        
        // Validate email is not in use
        emailNotInUse.validate(user);
        
        // Validate user has required fields
        if (user.getEmail() == null || user.getEmail().isBlank()) {
            throw new ApplicationException("Email is required");
        }
        
        if (user.getPasswordHash() == null || user.getPasswordHash().isBlank()) {
            throw new ApplicationException("Password is required");
        }
        
        if (user.getDisplayName() == null || user.getDisplayName().isBlank()) {
            throw new ApplicationException("Display name is required");
        }
    }
    
    //@Override
    protected void validateOnUpdate(Long id, User user) {
//        if (!userRepository.findById(positionId).isPresent()) {
//            throw new ApplicationException("User not found: " + positionId);
//        }
        
        // If email is being changed, ensure it's not already in use by another user
//        userRepository.findByEmail(user.getEmail())
//            .ifPresent(existingUser -> {
//                if (!existingUser.getId().equals(id)) {
//                    throw new ApplicationException("Email already in use by another user");
//                }
//            });
    }
}
