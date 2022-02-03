package com.max.movierating.service;

import com.max.movierating.entity.mail.MailMessage;
import com.max.movierating.entity.mail.MailTypeMessage;

public interface MailMessageService {

    MailMessage findByMailTypeMessage(MailTypeMessage mailTypeMessage);
}
