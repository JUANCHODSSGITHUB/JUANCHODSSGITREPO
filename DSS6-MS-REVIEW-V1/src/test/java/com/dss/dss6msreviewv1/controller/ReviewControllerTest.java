package com.dss.dss6msreviewv1.controller;

import com.dss.dss6msreviewv1.dto.ReviewDTO;
import com.dss.dss6msreviewv1.entity.Review;
import com.dss.dss6msreviewv1.service.ReviewService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(classes = ReviewControllerTest.class)
class ReviewControllerTest {
    @Mock
    private ReviewService reviewService;

    @InjectMocks
    private ReviewController reviewController;

    @Test
    void getReview() {
        Mockito.when(reviewService.viewReviewById(1)).thenReturn(mockReview());
        ResponseEntity<Review> response = reviewController.getReview(1);
        Assertions.assertEquals(HttpStatus.FOUND, response.getStatusCode());
    }

    @Test
    void getReviews() {
        List<Review> reviewList = new ArrayList<>();
        reviewList.add(mockReview());
        Mockito.when(reviewService.viewAllReviews()).thenReturn(reviewList);
        ResponseEntity<List<Review>> response = reviewController.getReviews();
        Assertions.assertEquals(HttpStatus.FOUND, response.getStatusCode());
    }

    @Test
    void getReviewsByMovieId() {
        List<Review> reviewList = new ArrayList<>();
        reviewList.add(mockReview());
        Mockito.when(reviewService.viewReviewsByMovieId(1)).thenReturn(reviewList);
        ResponseEntity<List<Review>> response = reviewController.getReviewsByMovieId(1);
        Assertions.assertEquals(HttpStatus.FOUND, response.getStatusCode());
    }

    @Test
    void deleteReview() {
        Mockito.when(reviewService.deleteReview(1)).thenReturn("Data successfully deleted.");
        ResponseEntity<String> response = reviewController.deleteReview(1);
        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    void insertReview() {
        Mockito.when(reviewService.addReview(mockReviewDTO())).thenReturn(mockReview());
        ResponseEntity<Review> response = reviewController.insertReview(mockReviewDTO());
        Assertions.assertEquals(HttpStatus.CREATED, response.getStatusCode());
    }

    @Test
    void updateReview() {
        Mockito.when(reviewService.updateReview(1, mockReviewDTO())).thenReturn("Data successfully updated.");
        ResponseEntity<String> response = reviewController.updateReview(1, mockReviewDTO());
        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());

    }

    private Review mockReview(){
        Review review = new Review();
        review.setMovieId(1);
        review.setRating(10);
        review.setDescription("Awesome");
        return review;
    }

    private ReviewDTO mockReviewDTO(){
        ReviewDTO reviewDto = new ReviewDTO();
        reviewDto.setMovieId(1);
        reviewDto.setRating(10);
        reviewDto.setDescription("Awesome");
        return reviewDto;
    }
}