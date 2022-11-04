package com.dss.dss6msreviewv1.service;

import com.dss.dss6msreviewv1.dto.ReviewDTO;
import com.dss.dss6msreviewv1.entity.Review;

import java.util.List;

public interface ReviewService {

    Review addReview(ReviewDTO reviewDTO);

    String deleteReview(int id);

    List<Review> viewReviewsByMovieId(int movieId);

    Review viewReviewById(int reviewId);

    List<Review> viewAllReviews();

    String updateReview(int id, ReviewDTO reviewDTO);

}
