package com.dss.dss4msmoviev1.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name="MOVIES")
@Entity
public class Movie {
    @Id
    @GeneratedValue
    @Column(name = "movie_id")
    private int movieId;

    @Column(name = "movie_title", nullable = false)
    private String movieTitle;

    @Column(name = "movie_image", nullable = false)
    private String movieImage;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
    @JoinTable(
            name = "actor_movie",
            joinColumns = {@JoinColumn(name = "movie_id")},
            inverseJoinColumns = {@JoinColumn(name = "actor_id")}
    )
    Set<Actor> actors = new HashSet<>();

    @Column(name = "movie_cost")
    private int movieCost;


    @Column(name = "movie_year", nullable = false)
    private int movieYear;


    public Movie(String movieTitle, String movieImage, Set<Actor> actors, int movieCost, int movieYear) {
        this.movieTitle = movieTitle;
        this.movieImage = movieImage;
        this.actors = actors;
        this.movieCost = movieCost;
        this.movieYear = movieYear;
    }


    @Override
    public String toString() {
        return "Movie{" +
                "movieId=" + movieId +
                ", movieTitle='" + movieTitle + '\'' +
                ", movieImage='" + movieImage + '\'' +
                ", actors=" + actors +
                ", movieCost=" + movieCost +
                ", movieYear=" + movieYear +
                '}';
    }

}
