package com.max.movierating.entity;

import java.io.Serializable;

/**
 * Base entity class for all entity of database.
 *
 * @author Maxim Semenko
 * @version 1.0
 */
public abstract class BaseEntity implements Serializable, Cloneable {

    @Override
    public BaseEntity clone() {
        try {
            return (BaseEntity) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new AssertionError();
        }
    }
}
