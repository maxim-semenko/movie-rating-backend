package com.max.movierating.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.hibernate.annotations.ColumnDefault;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.HashSet;
import java.util.Set;

@Entity
@Data
@ToString
@EqualsAndHashCode(callSuper = true)
@EntityListeners(AuditingEntityListener.class)
public class User extends BaseEntity {

    @NotNull
    @Size(min = 2, max = 25)
    private String firstname;

    @NotNull
    @Size(min = 2, max = 25)
    private String lastname;

    @NotNull
    @Size(min = 2, max = 25)
    private String username;

    @NotNull
    @Size(min = 8, max = 255)
    private String password;

    @NotNull
    @Size(min = 2, max = 25)
    private String email;

    @ColumnDefault(value = "0")
    private Integer balance = 0;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "users_roles",
            joinColumns = {@JoinColumn(name = "user_id")},
            inverseJoinColumns = {@JoinColumn(name = "role_id")})
    private Set<Role> roles = new HashSet<>();

}
