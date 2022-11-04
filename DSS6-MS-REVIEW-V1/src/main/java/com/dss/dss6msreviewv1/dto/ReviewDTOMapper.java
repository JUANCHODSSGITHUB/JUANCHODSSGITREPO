package com.dss.dss6msreviewv1.dto;

import com.dss.dss6msreviewv1.entity.Review;

public class ReviewDTOMapper {
    public static Review mapReview(ReviewDTO reviewDTO){
        Review review = new Review(reviewDTO.getReviewId(), reviewDTO.getMovieId(), reviewDTO.getDescription(), reviewDTO.getDatePosted(), reviewDTO.getRating());
        return review;
    }
}
