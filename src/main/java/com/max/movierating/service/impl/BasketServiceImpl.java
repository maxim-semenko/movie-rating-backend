package com.max.movierating.service.impl;

import com.max.movierating.entity.Basket;
import com.max.movierating.entity.Film;
import com.max.movierating.repository.BasketRepository;
import com.max.movierating.repository.FilmRepository;
import com.max.movierating.service.BasketService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;


/**
 * Basket Service implementation that realize {@link BasketService} interface.
 *
 * @author Maxim Semenko
 * @version 1.0
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class BasketServiceImpl implements BasketService {

    private final BasketRepository basketRepository;
    private final FilmRepository filmRepository;

    @Override
    public Basket findById(Long id) {
        return basketRepository.getById(id);
    }

    @Override
    public Basket addToBasket(Long basketId, Long filmId) {
        Basket basket = basketRepository.getById(basketId);
        Film film = filmRepository.getById(filmId);
        if (!basket.getFilmList().contains(film)) {
            basket.getFilmList().add(film);
            basket.setSumma(basket.getSumma() + film.getPrice());
        }

        return basketRepository.save(basket);
    }

    @Override
    public Basket deleteFromBasket(Long basketId, Long filmId) {
        Basket basket = basketRepository.getById(basketId);
        Film film = filmRepository.getById(filmId);

        basket.getFilmList().remove(film);
        basket.setSumma(basket.getSumma() - film.getPrice());

        return basketRepository.save(basket);
    }
}
