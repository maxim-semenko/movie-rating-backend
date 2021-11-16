package com.max.movierating.service;

import java.util.List;

/**
 * Default service that provide to work with services.
 *
 * @param <T> - entity that need to work
 * @param <V> - type of entity id
 * @author Maxim Semenko
 * @version 1.0
 */
public interface DefaultService<T, V> {

    /**
     * The method that returns all {@link T}.
     *
     * @return {@link List<T>} collection
     */
    List<T> findAll();

    /**
     * The method that returns object {@link T} by needed id.
     *
     * @return {@link T} object
     */
    T findById(V v);

    /**
     * The method that saves object {@link T}.
     *
     * @return {@link T} object
     */
    T save(T t);

    /**
     * The method that updates object {@link T}.
     *
     * @return {@link T} object
     */
    T update(T t, V v);

    /**
     * The method that delete object {@link T} from the database.
     *
     * @return {@link T} object
     */
    T deleteById(V v);

}