package com.dss.dss4msmoviev1.dto;

import java.util.HashSet;
import java.util.Set;

public class MovieDTO {

    private String movieTitle;

    private String movieImage;

    Set<ActorDTO> actors = new HashSet<>();

    private int movieCost;

    private int movieYear;

    public MovieDTO() {
    }

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

    @SuppressWarnings("unchecked")
    public String getMovieTitle() {
        return movieTitle;
    }
    @SuppressWarnings("unchecked")
    public void setMovieTitle(String movieTitle) {
        this.movieTitle = movieTitle;
    }
    @SuppressWarnings("unchecked")
    public String getMovieImage() {
        return movieImage;
    }
    @SuppressWarnings("unchecked")
    public void setMovieImage(String movieImage) {
        this.movieImage = movieImage;
    }
    @SuppressWarnings("unchecked")
    public Set<ActorDTO> getActors() {
        return actors;
    }
    @SuppressWarnings("unchecked")
    public void setActors(Set<ActorDTO> actors) {
        this.actors = actors;
    }
    @SuppressWarnings("unchecked")
    public int getMovieCost() {
        return movieCost;
    }
    @SuppressWarnings("unchecked")
    public void setMovieCost(int movieCost) {
        this.movieCost = movieCost;
    }
    @SuppressWarnings("unchecked")
    public int getMovieYear() {
        return movieYear;
    }
    @SuppressWarnings("unchecked")
    public void setMovieYear(int movieYear) {
        this.movieYear = movieYear;
    }
}
