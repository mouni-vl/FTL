package com.example.fantasy.core.persistence.service;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

public interface BaseService<T, ID extends Serializable> {
    
    T create(T entity);
    
    Optional<T> findById(ID id);
    
    List<T> findAll();
    
    T update(ID id, T entity);
    
    void delete(ID id);
}
