package com.max.movierating.service.impl;

import com.max.movierating.entity.mail.MailMessage;
import com.max.movierating.entity.mail.MailTypeMessage;
import com.max.movierating.exception.ResourceNotFoundException;
import com.max.movierating.repository.MailMessageRepository;
import com.max.movierating.service.MailMessageService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Slf4j
public class MailMessageServiceImpl implements MailMessageService {

    private final MailMessageRepository mailMessageRepository;

    @Autowired
    public MailMessageServiceImpl(MailMessageRepository mailMessageRepository) {
        this.mailMessageRepository = mailMessageRepository;
    }

    @Override
    public MailMessage findByMailTypeMessage(MailTypeMessage mailTypeMessage) {
        Optional<MailMessage> optionalMailMessage = mailMessageRepository.findByMailTypeMessage(mailTypeMessage);
        MailMessage mailMessage;

        if (optionalMailMessage.isPresent()) {
            mailMessage = optionalMailMessage.get();
        } else {
            log.error("Mail message not found");
            throw new ResourceNotFoundException("Mail message not found");
        }

        return mailMessage;
    }
}
