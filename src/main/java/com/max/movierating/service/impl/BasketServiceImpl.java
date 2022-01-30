package com.max.movierating.service.impl;

import com.max.movierating.entity.Basket;
import com.max.movierating.entity.Film;
import com.max.movierating.entity.User;
import com.max.movierating.repository.BasketRepository;
import com.max.movierating.repository.FilmRepository;
import com.max.movierating.repository.UserRepository;
import com.max.movierating.service.BasketService;
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
@Slf4j
public class BasketServiceImpl implements BasketService {

    private final BasketRepository basketRepository;
    private final FilmRepository filmRepository;
    private final UserRepository userRepository;

    @Autowired
    public BasketServiceImpl(BasketRepository basketRepository,
                             FilmRepository filmRepository,
                             UserRepository userRepository) {
        this.basketRepository = basketRepository;
        this.filmRepository = filmRepository;
        this.userRepository = userRepository;
    }

    @Override
    public Basket findById(Long id) {
//        Basket basket = userRepository.getById(id).getBasket();
        User user = userRepository.findById(id).get();
        Basket basket = basketRepository.findByUser(user).get();
        Double summa = 0.0;

        for (Film film : basket.getFilmList()) {
            summa += film.getPrice();
        }

        basket.setSumma(summa);
        basketRepository.save(basket);

        return basket;
    }

    @Override
    public Basket addToBasket(Long userId, Long filmId) {
//        Basket basket = userRepository.getById(userId).getBasket();
        User user = userRepository.findById(userId).get();
        Basket basket = basketRepository.findByUser(user).get();
        Film film = filmRepository.getById(filmId);

        if (!basket.getFilmList().contains(film)) {
            basket.getFilmList().add(film);
            basket.setSumma(basket.getSumma() + film.getPrice());
        }

        return basketRepository.save(basket);
    }

    @Override
    public Basket deleteFromBasket(Long userId, Long filmId) {
//        Basket basket = userRepository.getById(userId).getBasket();
        User user = userRepository.findById(userId).get();
        Basket basket = basketRepository.findByUser(user).get();

        Film film = filmRepository.getById(filmId);

        if (basket.getFilmList().contains(film)) {
            basket.getFilmList().remove(film);
            basket.setSumma(basket.getSumma() - film.getPrice());
        }

        return basketRepository.save(basket);
    }
}
