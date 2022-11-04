package com.dss.dss6msreviewv1.repository;

import com.dss.dss6msreviewv1.entity.Review;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReviewRepository extends JpaRepository<Review, Integer> {

    List<Review> findByMovieId(int movieId);
    Review findByReviewId(int reviewId);

}
