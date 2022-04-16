package com.max.movierating.service.impl;

import com.max.movierating.entity.Basket;
import com.max.movierating.entity.Film;
import com.max.movierating.entity.PurchaseStorage;
import com.max.movierating.entity.User;
import com.max.movierating.exception.BadRequestException;
import com.max.movierating.repository.BasketRepository;
import com.max.movierating.repository.FilmRepository;
import com.max.movierating.service.BasketService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * Basket Service implementation that realize BasketService interface {@link BasketService}.
 *
 * @author Maxim Semenko
 * @version 1.0
 */
@Service
@Slf4j
public class BasketServiceImpl implements BasketService {

    private final BasketRepository basketRepository;
    private final FilmRepository filmRepository;
    private final UserServiceImpl userService;
    private final PurchaseStorageServiceImpl purchaseStorageService;

    @Autowired
    public BasketServiceImpl(BasketRepository basketRepository,
                             FilmRepository filmRepository,
                             UserServiceImpl userService,
                             PurchaseStorageServiceImpl purchaseStorageService) {
        this.basketRepository = basketRepository;
        this.filmRepository = filmRepository;
        this.userService = userService;
        this.purchaseStorageService = purchaseStorageService;
    }

    @Override
    public Basket findById(Long id) {
        User user = userService.findById(id);
        return user.getBasket();
    }

    @Override
    public Basket addToBasket(Long userId, Long filmId) {
        User user = userService.findById(userId);
        Basket basket = user.getBasket();
        PurchaseStorage purchase = purchaseStorageService.findByUserId(userId);
        Film film = filmRepository.getById(filmId);

        if (!basket.getFilmList().contains(film) && !purchase.getFilmList().contains(film)) {
            basket.getFilmList().add(film);
            basket.setSumma(basket.getSumma() + film.getPrice());
        } else {
            throw new BadRequestException("Can't add the film");
        }

        return basketRepository.save(basket);
    }

    @Override
    public Basket deleteFromBasket(Long userId, Long filmId) {
        User user = userService.findById(userId);
        Basket basket = user.getBasket();
        Film film = filmRepository.getById(filmId);

        if (basket.getFilmList().contains(film)) {
            basket.getFilmList().remove(film);
            basket.setSumma(basket.getSumma() - film.getPrice());
        }

        return basketRepository.save(basket);
    }

}
