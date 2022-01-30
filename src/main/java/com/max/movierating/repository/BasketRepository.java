package com.max.movierating.repository;

import com.max.movierating.entity.Basket;
import com.max.movierating.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * BasketRepository for working with entity {@link Basket}.
 *
 * @author Maxim Semenko
 * @version 1.0
 */
@Repository
public interface BasketRepository extends JpaRepository<Basket, Long> {

    Optional<Basket> findByUser(User user);

    void deleteBasketByUser(User user);
}
