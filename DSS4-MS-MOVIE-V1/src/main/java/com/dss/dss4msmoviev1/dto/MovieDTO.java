package com.dss.dss4msmoviev1.dto;

import com.dss.dss4msmoviev1.entity.Actor;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

public class MovieDTO {

    private String movieTitle;

    private String movieImage;

    Set<ActorDTO> actors = new HashSet<>();

    private int movieCost;

    private int movieYear;


    public MovieDTO(String movieTitle, String movieImage, Set<ActorDTO> actors, int movieCost, int movieYear) {
        this.movieTitle = movieTitle;
        this.movieImage = movieImage;
        this.actors = actors;
        this.movieCost = movieCost;
        this.movieYear = movieYear;
    }

    @Override
    public String toString() {
        return "Movie{" +
                "movieTitle='" + movieTitle + '\'' +
                ", movieImage='" + movieImage + '\'' +
                ", actors=" + actors +
                ", movieCost=" + movieCost +
                ", movieYear=" + movieYear +
                '}';
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

    public Set<ActorDTO> getActors() {
        return actors;
    }

    public void setActors(Set<ActorDTO> actors) {
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
