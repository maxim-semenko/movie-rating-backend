package com.max.movierating.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.Size;

@Entity
@Data
@EqualsAndHashCode(callSuper = true)
public class Film extends BaseEntity {

    @Size(min = 1, max = 50)
    private String name;

    @Size(min = 8, max = 512)
    private String description;
    private String imageURL;
    private Integer timeInMinutes;
    private Integer year;
    private Float rating;
    private Float price;

    @ManyToOne
    @JoinColumn(name = "genre_id", referencedColumnName = "id")
    private Genre genre;

    @ManyToOne
    @JoinColumn(name = "country_id", referencedColumnName = "id")
    private Country country;

}
