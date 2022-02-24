package com.max.movierating.dto.entity;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
public class RequestPaymentDTO {
    @NotNull
    private Long userId;

    @NotNull
    private Integer emailCode;

}
