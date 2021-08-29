package com.max.movierating.controller;

import com.max.movierating.entity.Basket;
import com.max.movierating.service.impl.BasketServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping(value = "/api/v1/baskets/")
@RequiredArgsConstructor
public class BasketController {

    private final BasketServiceImpl basketService;

    @GetMapping("{id}")
    public ResponseEntity<Basket> findById(@PathVariable Long id) {
        return new ResponseEntity<>(basketService.findById(id), HttpStatus.OK);
    }

    @PostMapping("basket/{basketId}/film/{filmId}")
    public ResponseEntity<Basket> addToBasket(@PathVariable Long basketId, @PathVariable Long filmId) {
        return new ResponseEntity<>(basketService.addToBasket(basketId, filmId), HttpStatus.CREATED);
    }

    @DeleteMapping("basket/{basketId}/film/{filmId}")
    public ResponseEntity<Basket> deleteFromBasket(@PathVariable Long basketId, @PathVariable Long filmId) {
        return new ResponseEntity<>(basketService.deleteFromBasket(basketId, filmId), HttpStatus.OK);
    }
}
