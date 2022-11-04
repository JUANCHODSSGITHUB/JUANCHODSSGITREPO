package com.dss.dss4msmoviev1.controller;


import com.dss.dss4msmoviev1.dto.MovieDTO;
import com.dss.dss4msmoviev1.entity.Movie;
import com.dss.dss4msmoviev1.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/dss/api")
public class MovieController {

    @Autowired
    private MovieService movieService;

    @GetMapping("/movie/title?={movieTitle}")
    public List<Movie> getMovieByTitle (@PathVariable String movieTitle){
        List<Movie> movies = null;
        movies = movieService.getMoviesByTitle(movieTitle);
        return movies;
    }


    @GetMapping("/movie/year?={movieYear}")
    public List<Movie> getMovieByTitle (@PathVariable int movieYear){
        List<Movie> movies = null;
        movies = movieService.getMoviesByYear(movieYear);
        return movies;
    }


    @GetMapping("/movie/actorId?={actorId}")
    public List<Movie> getMovieByActorId (@PathVariable int actorId){
        List<Movie> movies = null;
        movies = movieService.getMoviesByActorId(actorId);
        return movies;
    }

    @GetMapping("/movie/{id}")
    public Movie getCourses(@PathVariable int id){
        Movie movie = movieService.getMovieById(id);
        return movie;
    }

    @GetMapping("/movie")
    public List<Movie> getCourses(){
        List movieList = movieService.getAllMovies();
        return movieList;
    }

    @DeleteMapping("/movie/{id}")
    public String delete(@PathVariable int id){
        return movieService.deleteMovie(id);
    }

    @PostMapping("/movie")
    public Movie addMovie(@RequestBody MovieDTO movieDTO){
        return movieService.addMovie(movieDTO);
    }

    @PutMapping("/movie/{id}")
    public String updateMovie(@PathVariable int id, @RequestBody MovieDTO movieDTO){
        return movieService.updateMovie(id, movieDTO);
    }



}
