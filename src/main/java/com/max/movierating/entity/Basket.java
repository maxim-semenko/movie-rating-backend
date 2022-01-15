package com.max.movierating.entity;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
public class Basket extends BaseEntity {

    public Basket() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

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


    @Override
    public String toString() {
        return "Basket{" +
                "id=" + id +
                ", summa=" + summa +
                ", filmList=" + filmList +
                '}';
    }
}
