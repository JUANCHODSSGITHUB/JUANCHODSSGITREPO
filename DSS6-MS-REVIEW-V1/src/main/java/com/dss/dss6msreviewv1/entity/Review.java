package com.dss.dss6msreviewv1.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name="REVIEWS")
@Entity
public class Review {

    @Id
    @GeneratedValue
    @Column(name = "review_id")
    private int reviewId;

    @Column(name = "movie_id")
    private int movieId;
    @Column(name="description", nullable = false)
    private String description;

    @Column(name="date_posted", nullable = false)
    private Date datePosted;

    @Column(name="rating", nullable = false)
    private int rating;

    public Review( int movieId,String description, Date datePosted, int rating) {
        this.movieId = movieId;
        this.description = description;
        this.datePosted = datePosted;
        this.rating = rating;
    }

    @Override
    public String toString() {
        return "Review{" +
                "reviewId=" + reviewId +
                ", movieId=" + movieId +
                ", description='" + description + '\'' +
                ", datePosted=" + datePosted +
                ", rating=" + rating +
                '}';
    }
}
