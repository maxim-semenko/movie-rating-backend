package com.max.movierating.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode(callSuper = false)
public class Film extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Size(min = 1, max = 50)
    @NotEmpty
    private String name;

    @Size(min = 20, max = 512)
    @NotEmpty
    private String description;

    @NotNull
    private String imageURL;

    @NotNull
    private Integer timeInMinutes;

    @NotNull
    private Integer year;

    @NotNull
    private Double rating;

    @NotNull
    private Double price;

    @ManyToOne
    @JoinColumn(name = "genre_id", referencedColumnName = "id")
    @NotNull
    private Genre genre;

    @ManyToOne
    @JoinColumn(name = "country_id", referencedColumnName = "id")
    @NotNull
    private Country country;

    @Override
    public String toString() {
        return "Film{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", imageURL='" + imageURL + '\'' +
                ", timeInMinutes=" + timeInMinutes +
                ", year=" + year +
                ", rating=" + rating +
                ", price=" + price +
                ", genre=" + genre +
                ", country=" + country +
                '}';
    }
}
