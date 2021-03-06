package com.max.movierating.dto.other;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
public class RequestRestorePasswordDTO {

    @NotBlank(message = "email can't be empty")
    private String email;

    @NotNull(message = "emailCode can't be empty")
    private Integer emailCode;

    @NotBlank(message = "newPassword can't be blank")
    private String newPassword;
}
