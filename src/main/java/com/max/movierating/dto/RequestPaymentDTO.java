package com.max.movierating.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
public class RequestPaymentDTO {
    @NotNull
    private Long userId;

//    @NotEmpty
//    private String cardNumber;
//
//    @NotEmpty
//    private String cardHolderName;
//
//    @NotEmpty
//    private String expirationMMYY;
//
//    @NotNull
//    private Integer CVV;

    @NotNull
    private Integer emailCode;

}
