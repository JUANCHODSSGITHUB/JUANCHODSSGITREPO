package com.dss.dss4msmoviev1.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MovieDTO {

    private String movieTitle;

    private String movieImage;

    Set<ActorDTO> actors = new HashSet<>();

    private int movieCost;

    private int movieYear;


    @Override
    public String toString() {
        return "Movie{" +
                "movieTitle='" + movieTitle + '\'' +
                ", movieImage='" + movieImage + '\'' +
                ", actors=" + actors +
                ", movieCost=" + movieCost +
                ", movieYear=" + movieYear +
                '}';
    }

}
