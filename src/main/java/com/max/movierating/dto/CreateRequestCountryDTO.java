package com.max.movierating.dto;

import com.max.movierating.entity.Country;
import lombok.Data;

@Data
public class CreateRequestCountryDTO {

    String name;

    Country toCountry() {
        return null;
    }
}
