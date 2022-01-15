package com.max.movierating.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;

@Getter
@Setter
public class RequestUpdatePasswordDTO {

    @NotEmpty(message = "Old password may not be empty")
    private String oldPassword;

    @NotEmpty(message = "New password may not be empty")
    private String newPassword;
}
