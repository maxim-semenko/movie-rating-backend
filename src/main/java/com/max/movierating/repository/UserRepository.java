package com.max.movierating.repository;

import com.max.movierating.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * UserRepository for working with entity {@link User}.
 *
 * @author Maxim Semenko
 * @version 1.0
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    Boolean existsByUsername(String login);

    Boolean existsByEmail(String email);

    Page<User> findAll(Pageable pageable);

    Optional<User> findByUsername(String username);

    Optional<User> findByEmail(String email);
}
