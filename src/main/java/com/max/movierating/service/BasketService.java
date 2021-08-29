package com.max.movierating.service;

import com.max.movierating.entity.Basket;

public interface BasketService {

    Basket findById(Long userId);

    Basket addToBasket(Long userId, Long filmId);

    Basket deleteFromBasket(Long userId, Long filmId);
}
