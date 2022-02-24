package com.max.movierating.dto.other;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class UpdatePasswordDTO {

    @NotBlank(message = "Old password may not be empty")
    private String oldPassword;

    @NotBlank(message = "New password may not be empty")
    private String newPassword;
}
