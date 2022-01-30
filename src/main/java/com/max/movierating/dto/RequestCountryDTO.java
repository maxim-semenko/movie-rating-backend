package com.max.movierating.dto;

import com.max.movierating.entity.Country;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class RequestCountryDTO {

    @NotBlank(message = "Name may not be empty")
    private String name;

    public Country toCountry() {
        return Country
                .builder()
                .name(name)
                .build();
    }
}
