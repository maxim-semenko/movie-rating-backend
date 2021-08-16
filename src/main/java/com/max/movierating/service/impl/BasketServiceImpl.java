package com.max.movierating.service.impl;

import com.max.movierating.entity.Basket;
import com.max.movierating.entity.Film;
import com.max.movierating.entity.User;
import com.max.movierating.repository.BasketRepository;
import com.max.movierating.repository.FilmRepository;
import com.max.movierating.repository.UserRepository;
import com.max.movierating.service.BasketService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
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
    private final UserRepository userRepository;
    private final FilmRepository filmRepository;

    @Override
    public Basket getById(Long id) {
        return basketRepository.getById(id);
    }

    @Override
    public Basket add(Long userId, Long filmId) {
        User user = userRepository.getById(userId);
        Film film = filmRepository.getById(filmId);
        Basket basket = user.getBasket();
        if (!basket.getFilmList().contains(film)) {
            basket.getFilmList().add(film);
            basket.setSumma(basket.getSumma() + film.getPrice());
        }

        return basketRepository.save(basket);
    }

    @Override
    public Basket delete(Long userId, Long filmId) {
        User user = userRepository.getById(userId);
        Film film = filmRepository.getById(filmId);
        Basket basket = user.getBasket();

        basket.getFilmList().remove(film);
        Float newSumma = basket.getSumma() - film.getPrice();
        basket.setSumma(newSumma);

        return basketRepository.save(basket);
    }
}
