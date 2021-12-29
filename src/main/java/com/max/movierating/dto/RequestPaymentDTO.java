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
    private String securityCardCode;
    private String emailCode;
    private List<Film> filmsList;
    private Double totalPrice;
    private Date date;
}
