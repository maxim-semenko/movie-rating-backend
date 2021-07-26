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
public class Role extends BaseEntity {

    @Enumerated(EnumType.STRING)
    @Column(unique = true, length = 20)
    private EnumRole name;

    public Role(EnumRole name) {
        this.name = name;
    }

    public Role() {

    }
}
