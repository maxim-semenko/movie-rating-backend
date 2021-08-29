package com.max.movierating.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.Size;
import java.util.HashSet;
import java.util.Set;

@Entity
@Data
@EqualsAndHashCode(callSuper = true)
public class Film extends BaseEntity {

    @Size(min = 1, max = 50)
    private String name;

    @Size(min = 8, max = 512)
    private String description;
    private String imageURL;
    private String country;
    private Integer timeInMinutes;
    private Integer year;
    private Float rating;
    private Float price;

    @ManyToOne
    @JoinColumn(name = "genre_id", referencedColumnName = "id")
    private Genre genre;

    @OneToMany(mappedBy = "film", fetch = FetchType.EAGER)
    private Set<Comment> comments = new HashSet<>();

}
