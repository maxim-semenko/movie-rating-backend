package com.max.movierating.controller;

import com.max.movierating.entity.Basket;
import com.max.movierating.service.impl.BasketServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping(value = "/api/v1/basket/")
public class BasketController {

    private final BasketServiceImpl basketService;

    @Autowired
    public BasketController(BasketServiceImpl basketService) {
        this.basketService = basketService;
    }

    @GetMapping("{id}")
    @PreAuthorize("hasAnyRole('USER', 'ADMIN') and #id == authentication.principal.id")
    public ResponseEntity<Basket> findById(@PathVariable Long id) {
        return new ResponseEntity<>(basketService.findById(id), HttpStatus.OK);
    }

    @PostMapping("user/{userId}/film/{filmId}")
    @PreAuthorize("hasAnyRole('USER', 'ADMIN') and #userId == authentication.principal.id")
    public ResponseEntity<Basket> addToBasket(@PathVariable Long userId, @PathVariable Long filmId) {
        return new ResponseEntity<>(basketService.addToBasket(userId, filmId), HttpStatus.OK);
    }

    @DeleteMapping("user/{userId}/film/{filmId}")
    @PreAuthorize("hasAnyRole('USER', 'ADMIN') and #userId == authentication.principal.id")
    public ResponseEntity<Basket> deleteFromBasket(@PathVariable Long userId, @PathVariable Long filmId) {
        return new ResponseEntity<>(basketService.deleteFromBasket(userId, filmId), HttpStatus.OK);
    }
}
