package com.dss.dss6msreviewv1.entity;


import javax.persistence.*;
import java.util.Date;

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

    public Review() {
    }

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

    public int getReviewId() {
        return reviewId;
    }

    public void setReviewId(int reviewId) {
        this.reviewId = reviewId;
    }

    public int getMovieId() {
        return movieId;
    }

    public void setMovieId(int movieId) {
        this.movieId = movieId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getDatePosted() {
        return datePosted;
    }

    public void setDatePosted(Date datePosted) {
        this.datePosted = datePosted;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }
}
