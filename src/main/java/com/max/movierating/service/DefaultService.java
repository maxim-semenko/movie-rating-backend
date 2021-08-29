package com.max.movierating.service;

import java.util.List;

public interface DefaultService<T> {

    /**
     * The method that returns all {@link T} from the database.
     *
     * @return {@link List<T>} collection
     */
    List<T> findAll();

    /**
     * The method that returns object {@link T} from the database.
     *
     * @return {@link T} object
     */
    T findById(Long id);

    /**
     * The method that saves object {@link T} in the database.
     *
     * @return {@link T} object
     */
    T save(T t);

    /**
     * The method that updates object {@link T} in the database.
     *
     * @return {@link T} object
     */
    T update(T t);

    /**
     * The method that delete object {@link T} from the database.
     *
     * @return {@link T} object
     */
    T deleteById(Long id);

}