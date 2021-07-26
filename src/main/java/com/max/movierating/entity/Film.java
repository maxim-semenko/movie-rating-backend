package com.max.movierating.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import java.util.HashSet;
import java.util.Set;

@Entity
@Data
@EqualsAndHashCode(callSuper = true)
public class Film extends BaseEntity {

    private String name;
    private String decryption;
    private Integer price;
    private Integer timeInMinutes;

    @ManyToOne
    @JoinColumn(name = "genre_id", referencedColumnName = "id")
    private Genre genre;

    @OneToMany(mappedBy = "film", fetch = FetchType.EAGER)
    private Set<Comment> comments = new HashSet<>();

}
