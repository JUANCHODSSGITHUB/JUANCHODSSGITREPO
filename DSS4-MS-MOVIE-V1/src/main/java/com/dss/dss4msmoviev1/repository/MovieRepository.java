package com.dss.dss4msmoviev1.repository;

import com.dss.dss4msmoviev1.entity.Movie;
import org.springframework.data.jpa.repository.JpaRepository;


import java.util.*;

public interface MovieRepository extends JpaRepository<Movie, Integer> {

    List<Movie> findByMovieTitle(String movieTitle);
    List<Movie> findByMovieYear(int movieYear);
    List<Movie> findAllByActorsActorId(int actorId);

}
