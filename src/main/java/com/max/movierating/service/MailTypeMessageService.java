package com.max.movierating.service;

import com.max.movierating.entity.MailTypeMessage;

public interface MailTypeMessageService {

    MailTypeMessage findByName(String name);
}
