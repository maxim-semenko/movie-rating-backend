package com.max.movierating.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Getter
@Setter
public class RequestMarkDTO {

    @NotNull
    private Long userId;

    @NotNull
    private Long filmId;

    @Min(1)
    @Max(10)
    @NotNull
    private Integer value;

}
