package com.max.movierating.service;

import com.max.movierating.entity.mail.MailTypeMessage;

public interface MailTypeMessageService {

    MailTypeMessage findByName(String name);
}
