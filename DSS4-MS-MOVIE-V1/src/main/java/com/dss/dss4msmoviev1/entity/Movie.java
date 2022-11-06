package com.dss.dss4msmoviev1.entity;


import com.dss.dss4msmoviev1.dto.ActorDTO;

import javax.persistence.*;
import java.util.*;


@Table(name="MOVIES")
@Entity
public class Movie {
    @Id
    @GeneratedValue
    @Column(name = "movie_id")
    private int movieId;

    @Column(name = "movie_title", nullable = false)
    private String movieTitle;

    @Column(name="movie_image", nullable=false)
    private String movieImage;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
    @JoinTable(
            name = "actor_movie",
            joinColumns = {@JoinColumn(name = "actor_id")},
            inverseJoinColumns = {@JoinColumn(name = "movie_id")}
    )
    Set<Actor> actors = new HashSet<>();

    @Column(name="movie_cost")
    private int movieCost;


    @Column(name="movie_year", nullable = false)
    private int movieYear;

    public Movie() {
    }

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

    public int getMovieId() {
        return movieId;
    }

    public void setMovieId(int movieId) {
        this.movieId = movieId;
    }

    public String getMovieTitle() {
        return movieTitle;
    }

    public void setMovieTitle(String movieTitle) {
        this.movieTitle = movieTitle;
    }

    public String getMovieImage() {
        return movieImage;
    }

    public void setMovieImage(String movieImage) {
        this.movieImage = movieImage;
    }

    public Set<Actor> getActors() {
        return actors;
    }

    public void setActors(Set<Actor> actors) {
        this.actors = actors;
    }

    public int getMovieCost() {
        return movieCost;
    }

    public void setMovieCost(int movieCost) {
        this.movieCost = movieCost;
    }

    public int getMovieYear() {
        return movieYear;
    }

    public void setMovieYear(int movieYear) {
        this.movieYear = movieYear;
    }
}
