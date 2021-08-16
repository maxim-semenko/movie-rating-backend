package com.max.movierating.controller;

import com.max.movierating.entity.Basket;
import com.max.movierating.service.impl.BasketServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
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
//@RequiredArgsConstructor
public class BasketController {

    private final BasketServiceImpl basketService;

    @Autowired
    public BasketController(BasketServiceImpl basketService) {
        this.basketService = basketService;
    }

    @GetMapping("{id}")
    public ResponseEntity<Basket> getById(@PathVariable Long id) {
        return new ResponseEntity<>(basketService.getById(id), HttpStatus.OK);
    }

    @PostMapping("user/{userId}/film/{filmId}")
    public ResponseEntity<Basket> add(@PathVariable Long userId, @PathVariable Long filmId) {
        return new ResponseEntity<>(basketService.add(userId, filmId), HttpStatus.CREATED);
    }

    @DeleteMapping("user/{userId}/film/{filmId}")
    public ResponseEntity<Basket> delete(@PathVariable Long userId, @PathVariable Long filmId) {
        return new ResponseEntity<>(basketService.delete(userId, filmId), HttpStatus.OK);
    }
}
