package com.max.movierating.controller;

import com.max.movierating.entity.Basket;
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
@RequestMapping(value = "/api/v1/baskets")
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
    @PreAuthorize("hasAnyRole('USER', 'ADMIN') and #id == authentication.principal.id")
    public ResponseEntity<Basket> findById(@PathVariable Long id) {
        return new ResponseEntity<>(basketService.findById(id), HttpStatus.OK);
    }

    /**
     * Method that adds film to basket {@link Basket}.
     *
     * @param id     basket' id (basket id === user id)
     * @param filmId film's id {@link com.max.movierating.entity.Film}
     * @return basket {@link Basket}
     */
    @PostMapping("/{id}/film/{filmId}")
    @PreAuthorize("hasAnyRole('USER', 'ADMIN') and #id == authentication.principal.id")
    public ResponseEntity<Basket> addToBasket(@PathVariable Long id, @PathVariable Long filmId) {
        return new ResponseEntity<>(basketService.addToBasket(id, filmId), HttpStatus.OK);
    }

    /**
     * Method that deletes film from basket {@link Basket}.
     *
     * @param id     basket' id (basket id === user id)
     * @param filmId film's id {@link com.max.movierating.entity.Film}
     * @return basket {@link Basket}
     */
    @DeleteMapping("/{id}/film/{filmId}")
    @PreAuthorize("hasAnyRole('USER', 'ADMIN') and #id == authentication.principal.id")
    public ResponseEntity<Basket> deleteFromBasket(@PathVariable Long id, @PathVariable Long filmId) {
        return new ResponseEntity<>(basketService.deleteFromBasket(id, filmId), HttpStatus.OK);
    }
}
