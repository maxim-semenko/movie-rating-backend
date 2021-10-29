package com.max.movierating.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import java.util.HashSet;
import java.util.Set;

@Entity
@Data
@EqualsAndHashCode(callSuper = true)
public class Basket extends BaseEntity {

    @ColumnDefault(value = "0")
    private Float summa = 0.0f;

    @ManyToMany(cascade = {CascadeType.ALL})
    @JoinTable(
            name = "baskets_films",
            joinColumns = {@JoinColumn(name = "basket_id")},
            inverseJoinColumns = {@JoinColumn(name = "film_id")}
    )
    private Set<Film> filmList = new HashSet<>();

}
