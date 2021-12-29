package com.max.movierating.entity;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@ToString
@RequiredArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class Basket extends BaseEntity {

    @ColumnDefault(value = "0")
    private Double summa = 0.0;

    @ManyToMany(cascade = {CascadeType.ALL})
    @JoinTable(
            name = "baskets_films",
            joinColumns = {@JoinColumn(name = "basket_id")},
            inverseJoinColumns = {@JoinColumn(name = "film_id")}
    )
    @ToString.Exclude
    private Set<Film> filmList = new HashSet<>();

}
