package com.dss.dss4msmoviev1.service;

import com.dss.dss4msmoviev1.entity.Movie;

import java.util.*;

public interface MovieService {
    List<Movie> getAllMovies();

    Movie getMovieById(int movieId);

    List<Movie> getMoviesByTitle(String movieTitle);

    List<Movie> getMoviesByYear(int movieYear);

    List<Movie> getMoviesByActorId(int actorId);

    String addMovie();

    String deleteMovie(int movieId);

    String updateMovie();

}
