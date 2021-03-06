package com.max.movierating.dto.other;


import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
public class RequestSendMessageDTO {

    @NotNull
    private String email;

    @NotBlank
    private String typeMessage;

}
