package com.max.movierating.dto.other;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class RequestDeleteAccountDTO {

    @NotBlank(message = "Password may not be empty")
    private String password;

}
