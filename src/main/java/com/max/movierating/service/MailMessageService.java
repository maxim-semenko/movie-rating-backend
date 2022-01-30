package com.max.movierating.service;

import com.max.movierating.entity.MailMessage;
import com.max.movierating.entity.MailTypeMessage;

public interface MailMessageService {

    MailMessage findByMailTypeMessage(MailTypeMessage mailTypeMessage);
}
