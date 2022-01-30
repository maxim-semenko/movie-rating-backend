package com.max.movierating.service.impl;

import com.max.movierating.entity.MailTypeMessage;
import com.max.movierating.exception.ResourceNotFoundException;
import com.max.movierating.repository.MailTypeMessageRepository;
import com.max.movierating.service.MailTypeMessageService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Slf4j
public class MailTypeMessageServiceImpl implements MailTypeMessageService {

    private final MailTypeMessageRepository mailTypeMessageRepository;

    @Autowired
    public MailTypeMessageServiceImpl(MailTypeMessageRepository mailTypeMessageRepository) {
        this.mailTypeMessageRepository = mailTypeMessageRepository;
    }

    @Override
    public MailTypeMessage findByName(String name) {
        Optional<MailTypeMessage> optionalMailTypeMessage = mailTypeMessageRepository.findByName(name);
        MailTypeMessage mailTypeMessage;

        if (optionalMailTypeMessage.isPresent()) {
            mailTypeMessage = optionalMailTypeMessage.get();
        } else {
            log.info("Mail type message not found");
            throw new ResourceNotFoundException("Mail type message not found");
        }
        return mailTypeMessage;
    }
}
