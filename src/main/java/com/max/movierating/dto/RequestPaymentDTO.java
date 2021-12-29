package com.max.movierating.dto;

import com.max.movierating.entity.Film;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Getter
@Setter
public class RequestPaymentDTO {
    private Long userId;
    private String numberCard;
    private String cardHolderName;
    private String expirationMMYY;
    private String securityCode;
    private String CodeFromEmail;
    private List<Film> filmsIdList;
    private Double total;
    private Date date;
}
