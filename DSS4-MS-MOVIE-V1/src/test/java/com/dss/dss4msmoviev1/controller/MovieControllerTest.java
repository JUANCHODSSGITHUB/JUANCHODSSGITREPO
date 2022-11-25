package com.dss.dss4msmoviev1.controller;

import com.dss.dss4msmoviev1.dto.ActorDTO;
import com.dss.dss4msmoviev1.dto.MovieDTO;
import com.dss.dss4msmoviev1.dto.UpdateMovieDTO;
import com.dss.dss4msmoviev1.entity.Actor;
import com.dss.dss4msmoviev1.entity.Movie;
import com.dss.dss4msmoviev1.service.MovieService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.mockito.ArgumentMatchers.*;

@SpringBootTest(classes = MovieControllerTest.class)
class MovieControllerTest {

    @Mock
    private MovieService movieService;

    @InjectMocks
    private MovieController movieController;

    @Test
    void getMoviesByTitle() {
        List<Movie> movieList = new ArrayList<>();
        movieList.add(mockMovie());
        Mockito.when(movieService.getMoviesByTitle("Shrek")).thenReturn(movieList);
        ResponseEntity<List<Movie>> response = movieController.getMoviesByTitle("Shrek");
        Assertions.assertEquals(HttpStatus.FOUND, response.getStatusCode());
    }

    @Test
    void getMoviesByYear() {
        List<Movie> movieList = new ArrayList<>();
        movieList.add(mockMovie());
        Mockito.when(movieService.getMoviesByYear(2003)).thenReturn(movieList);
        ResponseEntity<List<Movie>> response = movieController.getMoviesByYear(2003);
        Assertions.assertEquals(HttpStatus.FOUND, response.getStatusCode());
    }

    @Test
    void getMovieByActorId() {
        List<Movie> movieList = new ArrayList<>();
        movieList.add(mockMovie());
        Mockito.when(movieService.getMoviesByActorId(1)).thenReturn(movieList);
        ResponseEntity<List<Movie>> response = movieController.getMovieByActorId(1);
        Assertions.assertEquals(HttpStatus.FOUND, response.getStatusCode());
    }

    @Test
    void getMoviesById() {

        Mockito.when(movieService.getMovieById(1)).thenReturn(mockMovie());
        ResponseEntity<Movie> response = movieController.getMoviesById(1);
        Assertions.assertEquals(HttpStatus.FOUND, response.getStatusCode());
    }

    @Test
    void getMovies() {
        List<Movie> movieList = new ArrayList<>();
        movieList.add(mockMovie());
        Mockito.when(movieService.getAllMovies()).thenReturn(movieList);
        ResponseEntity<List<Movie>> response = movieController.getMovies();
        Assertions.assertEquals(HttpStatus.FOUND, response.getStatusCode());
    }

    @Test
    void delete() {
        Mockito.when(movieService.deleteMovie(anyInt())).thenReturn(anyString());
        ResponseEntity<String> response = movieController.delete(mockMovie().getMovieId());
        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    void addMovie() {
        Mockito.when(movieService.addMovie(mockMovieReq())).thenReturn(mockMovie());
        ResponseEntity<Movie> response = movieController.addMovie(mockMovieReq());
        Assertions.assertEquals(HttpStatus.CREATED, response.getStatusCode());
    }

    @Test
    void updateMovie() {
        Mockito.when(movieService.updateMovie(1,mockUpdateMovieDTO())).thenReturn("Data successfully updated.");
        ResponseEntity<String> response = movieController.updateMovie(1, mockUpdateMovieDTO());
        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());

    }

    private Movie mockMovie(){
        Movie movie = new Movie();
        movie.setMovieId(1);
        movie.setMovieImage("Shrek.jpg");
        movie.setMovieTitle("Shrek");
        movie.setActors(Collections.singleton(mockActors()));
        movie.setMovieCost(2300000);
        movie.setMovieYear(2022);
        return movie;
    }

    private Movie mockMovie1(){
        Movie movie = new Movie();
        movie.setMovieId(1);
        movie.setMovieImage("Shrek.jpg");
        movie.setMovieTitle("Shrek");
        movie.setActors(Collections.singleton(mockActors()));
        movie.setMovieCost(2300000);
        movie.setMovieYear(2020);
        return movie;
    }

    private MovieDTO mockMovieReq() {
        MovieDTO movie = new MovieDTO();
        movie.setMovieImage("Shrek.jpg");
        movie.setMovieTitle("Shrek");
        movie.setActors(Collections.singleton(mockActorsDTO()));
        movie.setMovieCost(2300000);
        movie.setMovieYear(2003);
        return movie;
    }

    private Actor mockActors(){
        Actor actor = new Actor();
        actor.setActorId(1);
        actor.setFirstName("Mike");
        actor.setLastName("Myers");
        actor.setGender('M');
        actor.setAge(45);
        return actor;
    }

    private ActorDTO mockActorsDTO(){
        ActorDTO actor = new ActorDTO();
        actor.setFirstName("Mike");
        actor.setLastName("Myers");
        actor.setGender('M');
        actor.setAge(45);
        return actor;
    }

    private UpdateMovieDTO mockUpdateMovieDTO(){
        UpdateMovieDTO updateMovieDTO = new UpdateMovieDTO();
        updateMovieDTO.setCost(300000000);
        updateMovieDTO.setImage("Shrek35.png");
        return updateMovieDTO;
    }
}