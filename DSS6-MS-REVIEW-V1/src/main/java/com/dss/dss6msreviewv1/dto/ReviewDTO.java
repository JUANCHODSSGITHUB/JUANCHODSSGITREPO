package com.dss.dss6msreviewv1.dto;
import java.util.Date;

public class ReviewDTO {

    private int movieId;
    private String description;

    private Date datePosted;

    private int rating;

    public ReviewDTO() {
    }

    public ReviewDTO( int movieId, String description, Date datePosted, int rating) {
        this.movieId = movieId;
        this.description = description;
        this.datePosted = datePosted;
        this.rating = rating;
    }

    @Override
    public String toString() {
        return "Review{" +
                " movieId=" + movieId +
                ", description='" + description + '\'' +
                ", datePosted=" + datePosted +
                ", rating=" + rating +
                '}';
    }

    @SuppressWarnings("unchecked")
    public int getMovieId() {
        return movieId;
    }
    @SuppressWarnings("unchecked")
    public void setMovieId(int movieId) {
        this.movieId = movieId;
    }
    @SuppressWarnings("unchecked")
    public String getDescription() {
        return description;
    }
    @SuppressWarnings("unchecked")
    public void setDescription(String description) {
        this.description = description;
    }
    @SuppressWarnings("unchecked")
    public Date getDatePosted() {
        return datePosted;
    }
    @SuppressWarnings("unchecked")
    public void setDatePosted(Date datePosted) {
        this.datePosted = datePosted;
    }
    @SuppressWarnings("unchecked")
    public int getRating() {
        return rating;
    }
    @SuppressWarnings("unchecked")
    public void setRating(int rating) {
        this.rating = rating;
    }
}
