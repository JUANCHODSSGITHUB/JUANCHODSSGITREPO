package com.dss.dss6msreviewv1.dto;

import com.dss.dss6msreviewv1.entity.Review;

public class ReviewDTOMapper {
    public Review mapReview(ReviewDTO reviewDTO){
        return new Review(reviewDTO.getMovieId(), reviewDTO.getDescription(), reviewDTO.getDatePosted(), reviewDTO.getRating());
    }
}
