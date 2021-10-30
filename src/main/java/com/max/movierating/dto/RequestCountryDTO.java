package com.max.movierating.dto;

import com.max.movierating.entity.Country;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RequestCountryDTO {
    private String name;

    public Country toCountry() {
        return new Country(name);
    }
}
