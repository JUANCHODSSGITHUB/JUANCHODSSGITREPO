package com.dss.dss6msreviewv1.dto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReviewDTO {

    private int movieId;
    private String description;

    private Date datePosted;

    private int rating;

    @Override
    public String toString() {
        return "Review{" +
                " movieId=" + movieId +
                ", description='" + description + '\'' +
                ", datePosted=" + datePosted +
                ", rating=" + rating +
                '}';
    }

}
