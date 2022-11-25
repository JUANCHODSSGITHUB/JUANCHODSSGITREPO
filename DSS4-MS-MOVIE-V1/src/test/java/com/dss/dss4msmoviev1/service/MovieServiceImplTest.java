package com.dss.dss4msmoviev1.service;

import com.dss.dss4msmoviev1.dto.ActorDTO;
import com.dss.dss4msmoviev1.dto.MovieDTO;
import com.dss.dss4msmoviev1.dto.UpdateMovieDTO;
import com.dss.dss4msmoviev1.entity.Actor;
import com.dss.dss4msmoviev1.entity.Movie;
import com.dss.dss4msmoviev1.exception.CannotDeleteMovieException;
import com.dss.dss4msmoviev1.exception.MovieNotFoundException;
import com.dss.dss4msmoviev1.repository.MovieRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;

@SpringBootTest
class MovieServiceImplTest {

    @Mock
    private MovieRepository movieRepository;

    @InjectMocks
    private MovieService movieService = new MovieServiceImpl();

    private static final Movie MOVIE = new Movie();
    private static final List<Movie> RES = new ArrayList<>();


    @Test
    void getAllMoviesNoMoviesNone() {
        Mockito.when(movieRepository.findAll()).thenReturn(RES);
        assertThrows(MovieNotFoundException.class, () -> movieService.getAllMovies());
    }

    @Test
    void getMovieById() {
        Optional<Movie> optionalMovie = Optional.of(mockMovie());
        Mockito.when(movieRepository.findById(1)).thenReturn(optionalMovie);
        assertNotNull( movieService.getMovieById(1));
        Mockito.verify(movieRepository).findById(1);
    }

    @Test
    void getMovieByIdFail() {
        Optional<Movie> optionalMovie = Optional.empty();
        Mockito.when(movieRepository.findById(1)).thenReturn(optionalMovie);
        assertThrows(MovieNotFoundException.class, () -> movieService.getMovieById(1));
    }

    @Test
    void getMoviesByTitle() {
        List<Movie> movieList = new ArrayList<>();
        movieList.add(mockMovie());
        Mockito.when(movieRepository.findByMovieTitle("Shrek")).thenReturn(movieList);
        assertNotNull( movieService.getMoviesByTitle("Shrek"));
        Mockito.verify(movieRepository).findByMovieTitle("Shrek");
    }

    @Test
    void getMoviesByTitleFail() {
        List<Movie> movieList = new ArrayList<>();
        Mockito.when(movieRepository.findByMovieTitle("Shrek")).thenReturn(movieList);
        assertThrows(MovieNotFoundException.class, () -> movieService.getMoviesByTitle("Shrek"));
    }


    @Test
    void getMoviesByYear() {
        List<Movie> movieList = new ArrayList<>();
        movieList.add(mockMovie());
        Mockito.when(movieRepository.findByMovieYear(2003)).thenReturn(movieList);
        assertNotNull( movieService.getMoviesByYear(2003));
        Mockito.verify(movieRepository).findByMovieYear(2003);
    }

    @Test
    void getMoviesByYearFail() {
        List<Movie> movieList = new ArrayList<>();
        Mockito.when(movieRepository.findByMovieYear(2003)).thenReturn(movieList);
        assertThrows(MovieNotFoundException.class, () -> movieService.getMoviesByYear(2003));
    }

    @Test
    void getMoviesByActorId() {
        List<Movie> movieList = new ArrayList<>();
        movieList.add(mockMovie());
        Mockito.when(movieRepository.findAllByActorsActorId(1)).thenReturn(movieList);
        assertNotNull( movieService.getMoviesByActorId(1));
        Mockito.verify(movieRepository).findAllByActorsActorId(1);
    }

    @Test
    void getMoviesByActorIdFail() {
        List<Movie> movieList = new ArrayList<>();
        Mockito.when(movieRepository.findAllByActorsActorId(1)).thenReturn(movieList);
        assertThrows(MovieNotFoundException.class, () -> movieService.getMoviesByActorId(1));
    }

    @Test
    void addMovie() {
        Mockito.when(movieRepository.save(any(Movie.class))).thenReturn(mockMovie());
        assertNotNull( movieService.addMovie(mockMovieReq()));
    }

    @Test
    void deleteMovie() {
        Optional<Movie> optionalMovie = Optional.of(mockMovie1());
        Mockito.when(movieRepository.findById(1)).thenReturn(optionalMovie);
        assertEquals("Data successfully deleted.", movieService.deleteMovie(1));
    }

    @Test
    void deleteMovieFail() {
        Optional<Movie> optionalMovie = Optional.empty();
        Mockito.when(movieRepository.findById(1)).thenReturn(optionalMovie);
        assertThrows(MovieNotFoundException.class, () -> movieService.deleteMovie(1));
    }

    @Test
    void deleteMovieFail2() {
        Optional<Movie> optionalMovie = Optional.of(mockMovie());
        Mockito.when(movieRepository.findById(1)).thenReturn(optionalMovie);
        assertThrows(CannotDeleteMovieException.class, () -> movieService.deleteMovie(1));
    }

    @Test
    void updateMovie() {
        Optional<Movie> optionalMovie = Optional.of(mockMovie1());
        Mockito.when(movieRepository.findById(1)).thenReturn(optionalMovie);
        assertEquals("Data successfully updated.", movieService.updateMovie(1, mockUpdateMovieDTO()));
    }

    @Test
    void updateMovieFail() {
        Optional<Movie> optionalMovie = Optional.empty();
        Mockito.when(movieRepository.findById(1)).thenReturn(optionalMovie);
        assertThrows(MovieNotFoundException.class, () -> movieService.updateMovie(1, mockUpdateMovieDTO()));
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