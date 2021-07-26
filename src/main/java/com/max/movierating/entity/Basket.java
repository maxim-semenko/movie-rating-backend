package com.max.movierating.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import java.util.HashSet;
import java.util.Set;

@Entity
@Data
@EqualsAndHashCode(callSuper = true)
public class Basket extends BaseEntity {

    @OneToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;

    private Integer summa;

    @ManyToMany(cascade = {CascadeType.ALL})
    @JoinTable(
            name = "basket_film",
            joinColumns = {@JoinColumn(name = "film_id")},
            inverseJoinColumns = {@JoinColumn(name = "basket_id")}
    )
    private Set<Film> filmList = new HashSet<>();
}
