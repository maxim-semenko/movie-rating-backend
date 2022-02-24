package com.max.movierating.dto.other;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RequestRestorePasswordDTO {
    private Long userId;
    private String newPassword;
    private Integer emailCode;
}
