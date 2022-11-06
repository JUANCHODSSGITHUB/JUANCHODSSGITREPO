package com.dss.dss4msmoviev1.service;

import com.dss.dss4msmoviev1.dto.MovieDTO;
import com.dss.dss4msmoviev1.dto.util.MovieDTOMapper;
import com.dss.dss4msmoviev1.entity.Movie;
import com.dss.dss4msmoviev1.repository.MovieRepository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.time.Year;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class MovieServiceImpl implements MovieService{

    private static final Logger LOG =   LoggerFactory.getLogger(MovieServiceImpl.class);
    @Autowired
    private MovieRepository movieRepository;

    @Override
    public List<Movie> getAllMovies() {
        List<Movie> movieList = null;
        movieList = movieRepository.findAll();
        return movieList;
    }

    @Override
    public Movie getMovieById(int movieId) {
        Movie movieFound = null;

        try{
            Optional<Movie> movie = movieRepository.findById(movieId);
            movieFound = movie.get();
        }catch (NoSuchElementException | NullPointerException e){
            LOG.error(e.getMessage());
        }

        return movieFound;
    }

    @Override
    public List<Movie> getMoviesByTitle(String movieTitle) {
        List<Movie> movieFound = null;
        try{
            movieFound = movieRepository.findByMovieTitle(movieTitle);
            movieFound.size(); //for checking
        }catch (NoSuchElementException | NullPointerException e){
            LOG.error(e.getMessage());
        }
        return movieFound;
    }

    @Override
    public List<Movie> getMoviesByYear(int movieYear) {
        List<Movie> movieFound = null;
        try{
            movieFound = movieRepository.findByMovieYear(movieYear);
            movieFound.size(); //for checking
        }catch (NoSuchElementException | NullPointerException e){
            LOG.error(e.getMessage());
        }
        return movieFound;
    }

    @Override
    public List<Movie> getMoviesByActorId(int actorId) {
        List<Movie> movieFound = null;
        try{
            movieFound = movieRepository.findAllByActorsActorId(actorId);
            movieFound.size(); //for checking
        }catch (NoSuchElementException | NullPointerException e){
            LOG.error(e.getMessage());
        }
        return movieFound;
    }

    @Override
    public Movie addMovie(MovieDTO movieDTO) {
        MovieDTOMapper movieDTOMapper = new MovieDTOMapper();
        Movie movie = movieDTOMapper.mapMovie(movieDTO);
        Movie newMovie = movieRepository.save(movie);
        return newMovie;
    }

    @Override
    public String deleteMovie(int movieId) {
        String responseMessage = null;
        Movie movieFound = null;
        int year = Year.now().getValue();
        try {
            Optional<Movie> movie = movieRepository.findById(movieId);
            movieFound = movie.get();
            if((year - movieFound.getMovieYear()) >= 1){
                movieRepository.deleteById(movieId);
                responseMessage = "Data successfully deleted.";
            }else{
                responseMessage = "Can't delete movie entry.";
            }
        }catch(EmptyResultDataAccessException | NoSuchElementException e){
            responseMessage = "No such data with id = " + movieId + ".";
            LOG.error(e.getMessage());
        }
        return responseMessage;
    }

    @Override
    public String updateMovie(int movieId, MovieDTO movieDTO) {
        String responseMessage = null;
        Movie movieFound = null;
        MovieDTOMapper movieDTOMapper = new MovieDTOMapper();

        try{
            Optional<Movie> movie = movieRepository.findById(movieId);
            movieFound = movie.get();
            Movie movieUpdate = movieDTOMapper.mapMovie(movieDTO);
            movieFound.setMovieCost(movieUpdate.getMovieCost());
            movieFound.setMovieImage(movieUpdate.getMovieImage());
            movieFound.setActors(movieUpdate.getActors());
            movieFound.setMovieTitle(movieUpdate.getMovieTitle());
            movieFound.setMovieYear(movieUpdate.getMovieYear());
            movieRepository.save(movieFound);
            responseMessage = "Data successfully updated.";

        }catch (NoSuchElementException | NullPointerException e){
            responseMessage = "No such data with id = " + movieId + ".";
            LOG.error(e.getMessage());
        }
        return responseMessage;
    }
}
