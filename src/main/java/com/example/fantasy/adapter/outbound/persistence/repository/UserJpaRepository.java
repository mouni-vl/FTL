package com.example.fantasy.adapter.outbound.persistence.repository;

import com.example.fantasy.adapter.outbound.persistence.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserJpaRepository
        //extends JpaRepository<UserEntity, Long>
        extends CrudRepository<UserEntity, Long>
{
    Optional<UserEntity> findByEmail(String email);
}
