package com.max.movierating.service;


import com.max.movierating.dto.other.RequestSendMessageDTO;

public interface MailService {

    Boolean performMessage(RequestSendMessageDTO requestSendMessageDTO);
}
