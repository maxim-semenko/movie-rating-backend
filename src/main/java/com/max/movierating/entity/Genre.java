package com.max.movierating.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.Entity;

@Entity
@Data
@ToString
@EqualsAndHashCode(callSuper = true)
public class Genre extends BaseEntity {

    private String name;

    public Genre() {

    }
}
