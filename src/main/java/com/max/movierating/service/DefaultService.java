package com.max.movierating.service;

import java.util.List;

public interface DefaultService<T> {

    List<T> findAll();

    T findById(Long id);

    T save(T t);

    T update(T t);

    Long deleteById(Long id);

}

