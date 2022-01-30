package com.max.movierating.dto;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RequestSendMessageDTO {
    private Long userId;
    private String typeMessage;
}
