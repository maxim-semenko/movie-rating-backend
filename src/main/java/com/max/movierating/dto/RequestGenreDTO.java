package com.max.movierating.dto;

import com.max.movierating.entity.Genre;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RequestGenreDTO {
    private String name;

    public Genre toGenre() {
        return new Genre(name);
    }
}
