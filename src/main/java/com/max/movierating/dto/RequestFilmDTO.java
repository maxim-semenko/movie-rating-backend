package com.max.movierating.dto;

import com.max.movierating.entity.Country;
import com.max.movierating.entity.Film;
import com.max.movierating.entity.Genre;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Setter
public class RequestFilmDTO {

    @NotNull
    @Size(min = 1, max = 50)
    private String name;

    @NotNull
    @Size(min = 20, max = 512)
    private String description;

    @NotNull
    private String imageURL;

    @NotNull
    private Integer timeInMinutes;

    @NotNull
    private Integer year;

    @NotNull
    private Double price;

    @NotNull
    private Genre genre;

    @NotNull
    private Country country;

    public Film toFilm() {
        return Film.builder().name(name)
                .description(description)
                .imageURL(imageURL)
                .timeInMinutes(timeInMinutes)
                .year(year)
                .price(price)
                .genre(genre)
                .country(country)
                .build();
    }
}
