package com.max.movierating.dto.entity;

import com.max.movierating.entity.Country;
import com.max.movierating.entity.Film;
import com.max.movierating.entity.Genre;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Set;

@Getter
@Setter
public class RequestFilmDTO {

    @NotBlank
    @Size(min = 1, max = 50)
    private String name;

    @NotBlank
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
    private Set<Genre> genres;

    @NotNull
    private Set<Country> countries;

    public Film toFilm() {
        return Film.builder().name(name)
                .description(description)
                .imageURL(imageURL)
                .timeInMinutes(timeInMinutes)
                .year(year)
                .price(price)
                .genres(genres)
                .countries(countries)
                .build();
    }

}
