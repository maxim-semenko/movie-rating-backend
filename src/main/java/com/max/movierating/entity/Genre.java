package com.max.movierating.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Entity
@Data
@ToString
@EqualsAndHashCode(callSuper = true)
public class Genre extends BaseEntity {

    @Enumerated(EnumType.STRING)
    @Column(unique = true, length = 20)
    private EnumGenre name;

    public Genre(EnumGenre name) {
        this.name = name;
    }

    public Genre() {

    }
}
