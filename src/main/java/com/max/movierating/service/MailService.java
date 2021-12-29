package com.max.movierating.service;

public interface MailService {

    void sendMessage(String emailTo, String subject, String message);
}
