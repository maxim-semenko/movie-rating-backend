package com.max.movierating.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
public class RequestUpdatePasswordDTO {

    @NotNull
    private String oldPassword;

    @NotNull
    private String newPassword;
}
