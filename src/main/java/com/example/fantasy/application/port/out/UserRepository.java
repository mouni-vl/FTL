package com.example.fantasy.application.port.out;

import com.example.fantasy.domain.model.User;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository // delete later
public interface UserRepository
        //extends BaseRepository<User, Long>
        {
    Optional<User> findByEmail(String email);

            User getReferenceById(Long aLong);

            <S extends User> Optional<S> findOne(Example<S> example);
        }
