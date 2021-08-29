package com.max.movierating.repository;

import com.max.movierating.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    //    Optional<User> findByLoginOrEmail(String login, String email);
    Boolean existsByUsername(String login);

    Boolean existsByEmail(String email);

    User findByUsername(String username);

}
