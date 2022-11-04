package com.dss.dss6msreviewv1.dto;

import javax.persistence.Column;
import java.util.Date;

public class ReviewDTO {
    private int reviewId;

    private int movieId;
    private String description;

    private Date datePosted;

    private int rating;

    public ReviewDTO() {
    }

    public ReviewDTO(int reviewId, int movieId, String description, Date datePosted, int rating) {
        this.reviewId = reviewId;
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
