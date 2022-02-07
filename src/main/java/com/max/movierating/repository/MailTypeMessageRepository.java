package com.max.movierating.repository;

import com.max.movierating.entity.Mark;
import com.max.movierating.entity.mail.MailTypeMessage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * MailTypeMessageRepository for working with entity {@link MailTypeMessage}.
 *
 * @author Maxim Semenko
 * @version 1.0
 */
@Repository
public interface MailTypeMessageRepository extends JpaRepository<MailTypeMessage, Long> {

    Optional<MailTypeMessage> findByName(String name);
}
