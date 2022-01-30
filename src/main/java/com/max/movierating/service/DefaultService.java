package com.max.movierating.service;

import com.max.movierating.entity.BaseEntity;

import java.util.List;

/**
 * Default service that provide to work with services.
 *
 * @param <T> - entity that need to work
 * @param <V> - type of entity id
 * @author Maxim Semenko
 * @version 1.0
 */
public interface DefaultService<T extends BaseEntity, V> {

    /**
     * The method that returns all objects {@link T} from the storage.
     *
     * @return {@link List<T>} founded object's - collection
     */
    List<T> findAll();

    /**
     * The method that returns object {@link T} from the storage by needed id.
     *
     * @return {@link T} founded object
     */
    T findById(V v);

    /**
     * The method that saves object {@link T} to the storage.
     *
     * @return {@link T} saved object
     */
    T save(T t);

    /**
     * The method that updates object {@link T} in the storage.
     *
     * @return {@link T} updated object
     */
    T update(T t, V v);

    /**
     * The method that deletes object {@link T} from the storage.
     *
     * @return {@link T} deleted object
     */
    T deleteById(V v);

}