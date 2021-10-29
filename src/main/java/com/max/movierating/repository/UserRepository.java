package com.max.movierating.repository;

import com.max.movierating.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    Boolean existsByUsername(String login);

    Boolean existsByEmail(String email);

    Page<User> findAll(Pageable pageable);

    User findByUsername(String username);

}
