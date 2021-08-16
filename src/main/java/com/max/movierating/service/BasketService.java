package com.max.movierating.service;

import com.max.movierating.entity.Basket;

public interface BasketService {

    Basket getById(Long userId);

    Basket add(Long userId, Long filmId);

    Basket delete(Long userId, Long filmId);
}
