package com.max.movierating.controller;

import com.max.movierating.constant.APIConstant;
import com.max.movierating.entity.Basket;
import com.max.movierating.entity.Film;
import com.max.movierating.service.impl.BasketServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * REST controller for baskets requests.
 *
 * @author Maxim Semenko
 * @version 1.0
 */
@RestController
@RequestMapping(value = APIConstant.BASKETS_API)
public class BasketController {

    /**
     * Basket service for working with basket {@link Basket}.
     */
    private final BasketServiceImpl basketService;

    @Autowired
    public BasketController(BasketServiceImpl basketService) {
        this.basketService = basketService;
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('USER') and #id == authentication.principal.id")
    public ResponseEntity<Basket> findById(@PathVariable Long id) {
        return new ResponseEntity<>(basketService.findById(id), HttpStatus.OK);
    }

    /**
     * Method that adds film to basket {@link Basket}.
     *
     * @param userId user's id
     * @param filmId film's id {@link Film}
     * @return basket {@link Basket}
     */
    @PostMapping("/user/{userId}/film/{filmId}")
    @PreAuthorize("hasRole('USER') and #userId == authentication.principal.id")
    public ResponseEntity<Basket> addToBasket(@PathVariable Long userId, @PathVariable Long filmId) {
        return new ResponseEntity<>(basketService.addToBasket(userId, filmId), HttpStatus.OK);
    }

    /**
     * Method that deletes film from basket {@link Basket}.
     *
     * @param userId user's id
     * @param filmId film's id {@link Film}
     * @return basket {@link Basket}
     */
    @DeleteMapping("/user/{userId}/film/{filmId}")
    @PreAuthorize("hasRole('USER') and #userId == authentication.principal.id")
    public ResponseEntity<Basket> deleteFromBasket(@PathVariable Long userId, @PathVariable Long filmId) {
        return new ResponseEntity<>(basketService.deleteFromBasket(userId, filmId), HttpStatus.OK);
    }
}
