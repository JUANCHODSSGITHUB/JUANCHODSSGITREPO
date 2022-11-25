package com.dss.dss4msmoviev1.service;

import com.dss.dss4msmoviev1.dto.MovieDTO;
import com.dss.dss4msmoviev1.dto.UpdateMovieDTO;
import com.dss.dss4msmoviev1.entity.Movie;

import java.util.*;

public interface MovieService {
    List<Movie> getAllMovies();

    Movie getMovieById(int movieId);

    List<Movie> getMoviesByTitle(String movieTitle);

    List<Movie> getMoviesByYear(int movieYear);

    List<Movie> getMoviesByActorId(int actorId);

    Movie addMovie(MovieDTO movieDTO);

    String deleteMovie(int movieId);

    String updateMovie(int movieId, UpdateMovieDTO updateMovieDTO);

}
