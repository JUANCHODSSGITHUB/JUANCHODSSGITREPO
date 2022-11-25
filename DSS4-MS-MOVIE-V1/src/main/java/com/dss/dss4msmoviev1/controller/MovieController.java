package com.dss.dss4msmoviev1.controller;


import com.dss.dss4msmoviev1.dto.MovieDTO;
import com.dss.dss4msmoviev1.dto.UpdateMovieDTO;
import com.dss.dss4msmoviev1.entity.Movie;
import com.dss.dss4msmoviev1.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/dss/api")
public class MovieController {

    @Autowired
    private MovieService movieService;

    @GetMapping("/movie/title/{movieTitle}")
    public ResponseEntity<List<Movie>> getMoviesByTitle (@PathVariable String movieTitle){
        List<Movie> movies = null;
        movies = movieService.getMoviesByTitle(movieTitle);
        return new ResponseEntity<>(movies, HttpStatus.FOUND);
    }


    @GetMapping("/movie/year/{movieYear}")
    public ResponseEntity<List<Movie>> getMoviesByYear (@PathVariable int movieYear){
        List<Movie> movies = null;
        movies = movieService.getMoviesByYear(movieYear);
        return new ResponseEntity<>(movies, HttpStatus.FOUND);
    }


    @GetMapping("/movie/actorId/{actorId}")
    public ResponseEntity<List<Movie>> getMovieByActorId (@PathVariable int actorId){
        List<Movie> movies = null;
        movies = movieService.getMoviesByActorId(actorId);
        return new ResponseEntity<>(movies, HttpStatus.FOUND);
    }

    @GetMapping("/movie/{id}")
    public ResponseEntity<Movie> getMoviesById(@PathVariable int id){
        Movie movie = movieService.getMovieById(id);
        return new ResponseEntity<>(movie, HttpStatus.FOUND);
    }

    @GetMapping("/movie")
    public ResponseEntity<List<Movie>> getMovies(){
        List<Movie> movieList = movieService.getAllMovies();
        return new ResponseEntity<>(movieList, HttpStatus.FOUND);
    }

    @DeleteMapping("/movie/{id}")
    public ResponseEntity<String> delete(@PathVariable int id){
        return new ResponseEntity<>(movieService.deleteMovie(id), HttpStatus.OK);
    }

    @PostMapping("/movie")
    public ResponseEntity<Movie> addMovie(@RequestBody MovieDTO movieDTO){
        return new ResponseEntity<>(movieService.addMovie(movieDTO), HttpStatus.CREATED);
    }

    @PutMapping("/movie/{id}")
    public ResponseEntity<String> updateMovie(@PathVariable int id, @RequestBody UpdateMovieDTO movieDTO){
        return new ResponseEntity<> (movieService.updateMovie(id, movieDTO), HttpStatus.OK);
    }



}
