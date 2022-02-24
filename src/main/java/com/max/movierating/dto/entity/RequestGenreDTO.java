package com.max.movierating.dto.entity;

import com.max.movierating.entity.Genre;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class RequestGenreDTO {

    @NotBlank(message = "Name may not be empty")
    private String name;

    public Genre toGenre() {
        return Genre.builder().name(name).build();
    }
}
