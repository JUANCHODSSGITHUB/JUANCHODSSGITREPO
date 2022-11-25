package com.dss.dss4msmoviev1.service;

import com.dss.dss4msmoviev1.dto.MovieDTO;
import com.dss.dss4msmoviev1.dto.UpdateMovieDTO;
import com.dss.dss4msmoviev1.dto.util.MovieDTOMapper;
import com.dss.dss4msmoviev1.entity.Movie;
import com.dss.dss4msmoviev1.exception.CannotDeleteMovieException;
import com.dss.dss4msmoviev1.exception.MovieNotFoundException;
import com.dss.dss4msmoviev1.repository.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Year;
import java.util.List;
import java.util.Optional;

@Service
public class MovieServiceImpl implements MovieService{

    @Autowired
    private MovieRepository movieRepository;

    private static final String NO_MOVIE_MESSAGE = "No movie found with id:";


    @Override
    public List<Movie> getAllMovies() {
        List<Movie> movieList = null;
        movieList = movieRepository.findAll();
        if(movieList.isEmpty()){
            throw new MovieNotFoundException("No movies found.");
        }
        return movieList;
    }

    @Override
    public Movie getMovieById(int movieId) {
        Movie movieFound = null;

        Optional<Movie> movie = movieRepository.findById(movieId);
        if(!movie.isEmpty()) {
            movieFound = movie.get();
        }else{
            throw new MovieNotFoundException( NO_MOVIE_MESSAGE + movieId + ".");
        }

        return movieFound;
    }

    @Override
    public List<Movie> getMoviesByTitle(String movieTitle) {
        List<Movie> movieFound = null;

        movieFound = movieRepository.findByMovieTitle(movieTitle);

        if(!movieFound.isEmpty()){
            return movieFound;
        }else{
            throw new MovieNotFoundException("No movies found with title:" + movieTitle + ".");
        }

    }

    @Override
    public List<Movie> getMoviesByYear(int movieYear) {
        List<Movie> movieFound = null;
        movieFound = movieRepository.findByMovieYear(movieYear);

        if(!movieFound.isEmpty()){
            return movieFound;
        }else{
            throw new MovieNotFoundException("No movies found with year:" + movieYear + ".");
        }
    }

    @Override
    public List<Movie> getMoviesByActorId(int actorId) {
        List<Movie> movieFound = null;
        movieFound = movieRepository.findAllByActorsActorId(actorId);

        if(!movieFound.isEmpty()){
            return movieFound;
        }else{
            throw new MovieNotFoundException("No movies found with actor ID:" + actorId + ".");
        }

    }
    @Override
    public Movie addMovie(MovieDTO movieDTO) {
        Movie movie = MovieDTOMapper.mapMovie(movieDTO);
        return movieRepository.save(movie);
    }

    @Override
    public String deleteMovie(int movieId) {
        String responseMessage = null;
        Movie movieFound = null;
        int year = Year.now().getValue();

        Optional<Movie> movie = movieRepository.findById(movieId);

        if(!movie.isEmpty()) {
            movieFound = movie.get();
            if ((year - movieFound.getMovieYear()) >= 1) {
                movieRepository.deleteById(movieId);
                responseMessage = "Data successfully deleted.";
                return responseMessage;
            } else {
                responseMessage = "Can't delete movie entry.";
                throw new CannotDeleteMovieException(responseMessage);
            }
        }else{
            throw new MovieNotFoundException(NO_MOVIE_MESSAGE + movieId + ".");
        }

    }

    @Override
    public String updateMovie(int movieId, UpdateMovieDTO movieDTO) {
        String responseMessage = null;
        Movie movieFound = null;
        Optional<Movie> movie = movieRepository.findById(movieId);

        if(!movie.isEmpty()) {
            movieFound = movie.get();
            movieFound.setMovieCost(movieDTO.getCost());
            movieFound.setMovieImage(movieDTO.getImage());
            movieRepository.save(movieFound);
            responseMessage = "Data successfully updated.";
            return responseMessage;
        }else{
            throw new MovieNotFoundException(NO_MOVIE_MESSAGE + movieId + ".");
        }


    }
}
