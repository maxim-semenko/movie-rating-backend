package com.max.movierating.repository;

import com.max.movierating.entity.MailTypeMessage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MailTypeMessageRepository extends JpaRepository<MailTypeMessage, Long> {

    Optional<MailTypeMessage> findByName(String name);
}
