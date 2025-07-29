package com.example.fantasy.core.rest.controller;

/**
 * Combined interface for CRUD operations
 * @param <D> Domain type
 * @param <ID> ID type
 * @param <F> Filter type
 * @param <C> Create DTO type
 * @param <U> Update DTO type
 * @param <R> Response DTO type
 */
public interface CrudResource<D, ID, F, C, U, R> extends 
    ReadableResource<D, ID, R>,
    ListableResource<D, ID, F, R>,
    WritableResource<D, ID, C, U> {
}
