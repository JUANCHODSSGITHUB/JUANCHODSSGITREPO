package com.dss.dss4msmoviev1.dto.util;

import com.dss.dss4msmoviev1.dto.ActorDTO;
import com.dss.dss4msmoviev1.dto.MovieDTO;
import com.dss.dss4msmoviev1.entity.Actor;
import com.dss.dss4msmoviev1.entity.Movie;

import java.util.stream.Collectors;

public class MovieDTOMapper {

    private MovieDTOMapper() {
        throw new IllegalStateException("Utility class");
    }
    public static Movie mapMovie(MovieDTO movieDTO){

        return new Movie(movieDTO.getMovieTitle(), movieDTO.getMovieImage(), movieDTO.getActors().parallelStream()
                .map(actorDTO -> mapActor(actorDTO))
                .collect(Collectors.toSet()),
                movieDTO.getMovieCost(), movieDTO.getMovieYear());
    }

    public static Actor mapActor(ActorDTO actorDTO){
        return new Actor(actorDTO.getFirstName(), actorDTO.getLastName(),
                actorDTO.getAge(), actorDTO.getGender());
        }
}
