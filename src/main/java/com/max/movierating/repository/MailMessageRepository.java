package com.max.movierating.repository;

import com.max.movierating.entity.mail.MailMessage;
import com.max.movierating.entity.mail.MailTypeMessage;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MailMessageRepository extends JpaRepository<MailMessage, Long> {

//    Optional<MailMessage> findByTypeMessage(MailTypeMessage typeMessage);

    Optional<MailMessage> findByMailTypeMessage(MailTypeMessage mailTypeMessage);
}
