package com.example.fantasy.core.rest.controller;

/**
 * Base interface for all resource controllers
 * @param <D> Domain type
 * @param <ID> ID type
 */
public interface ResourceController<D, ID> {
    String getResourceName();
}
