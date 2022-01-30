package com.max.movierating.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RestorePasswordDTO {
    private Long userId;
    private String newPassword;
    private Integer emailCode;
}
