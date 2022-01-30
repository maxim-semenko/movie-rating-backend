package com.max.movierating.service;


import com.max.movierating.dto.RequestSendMessageDTO;

public interface MailService {

    Boolean performMessage(RequestSendMessageDTO requestSendMessageDTO);
}
