package com.dss.dss6msreviewv1.service;

import com.dss.dss6msreviewv1.dto.ReviewDTO;
import com.dss.dss6msreviewv1.entity.Review;
import com.dss.dss6msreviewv1.exception.MovieNotFoundException;
import com.dss.dss6msreviewv1.exception.ReviewNotFoundException;
import com.dss.dss6msreviewv1.repository.ReviewRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;

@SpringBootTest
class ReviewServiceImplTest {

    @Mock
    private ReviewRepository reviewRepository;

    @InjectMocks
    private ReviewService reviewService = new ReviewServiceImpl();


    @Test
    void addReview() {
        Mockito.when(reviewRepository.save(any(Review.class))).thenReturn(mockReview());
        assertNotNull( reviewService.addReview(mockReviewDTO()));
    }

    @Test
    void addReviewFail() {
        Mockito.when(reviewRepository.save(any(Review.class))).thenThrow(RuntimeException.class);
        ReviewDTO reviewDTO = mockReviewDTO();
        assertThrows(MovieNotFoundException.class, () -> reviewService.addReview(reviewDTO));

    }

    @Test
    void deleteReview() {
        Optional<Review> optionalReview = Optional.of(mockReview());
        Mockito.when(reviewRepository.findById(1)).thenReturn(optionalReview);
        assertEquals("Data successfully deleted.", reviewService.deleteReview(1));
    }

    @Test
    void deleteReviewFail() {
        Optional<Review> optionalReview = Optional.empty();
        Mockito.when(reviewRepository.findById(1)).thenReturn(optionalReview);
        assertThrows(ReviewNotFoundException.class, () -> reviewService.deleteReview(1));
    }

    @Test
    void viewReviewsByMovieId() {
        List<Review> reviews = new ArrayList<>();
        reviews.add(mockReview());
        Mockito.when(reviewRepository.findByMovieId(1)).thenReturn(reviews);
        assertNotNull(reviewService.viewReviewsByMovieId(1));
        Mockito.verify(reviewRepository).findByMovieId(1);
    }

    @Test
    void viewReviewsByMovieIdFail() {
        List<Review> reviews = new ArrayList<>();
        Mockito.when(reviewRepository.findByMovieId(1)).thenReturn(reviews);
        assertThrows(MovieNotFoundException.class, () -> reviewService.viewReviewsByMovieId(1));
    }

    @Test
    void viewReviewById() {
        Optional<Review> optionalReview = Optional.of(mockReview());
        Mockito.when(reviewRepository.findById(1)).thenReturn(optionalReview);
        assertNotNull( reviewService.viewReviewById(1));
        Mockito.verify(reviewRepository).findById(1);
    }

    @Test
    void viewReviewByIdFail() {
        Optional<Review> optionalReview = Optional.empty();
        Mockito.when(reviewRepository.findById(1)).thenReturn(optionalReview);
        assertThrows(ReviewNotFoundException.class, () -> reviewService.viewReviewById(1));
    }

    @Test
    void viewAllReviews() {
        List<Review> reviews = new ArrayList<>();
        reviews.add(mockReview());
        Mockito.when(reviewRepository.findAll()).thenReturn(reviews);
        assertNotNull(reviewService.viewAllReviews());
        Mockito.verify(reviewRepository).findAll();
    }

    @Test
    void viewAllReviewsFail() {
        List<Review> reviews = new ArrayList<>();
        Mockito.when(reviewRepository.findAll()).thenReturn(reviews);
        assertThrows(ReviewNotFoundException.class, () -> reviewService.viewAllReviews());
    }

    @Test
    void updateReview() {
        Optional<Review> optionalReview = Optional.of(mockReview());
        Mockito.when(reviewRepository.findById(1)).thenReturn(optionalReview);
        assertEquals("Data successfully updated.", reviewService.updateReview(1, mockReviewDTO()));

    }

    @Test
    void updateReviewFail() {
        Optional<Review> optionalReview = Optional.empty();
        Mockito.when(reviewRepository.findById(1)).thenReturn(optionalReview);
        ReviewDTO reviewDTO = mockReviewDTO();
        assertThrows(ReviewNotFoundException.class, () -> reviewService.updateReview(1, reviewDTO));
    }

    @Test
    void updateReviewFail1(){
        Optional<Review> optionalReview = Optional.of(mockReview());
        Mockito.when(reviewRepository.findById(1)).thenReturn(optionalReview);
        Mockito.when(reviewRepository.save(any(Review.class))).thenThrow(RuntimeException.class);
        ReviewDTO reviewDTO = mockReviewDTO();
        MovieNotFoundException exception = assertThrows(MovieNotFoundException.class,
                () -> reviewService.updateReview(1, reviewDTO));
        assertEquals("No movie found with id: " + mockReviewDTO().getMovieId(), exception.getMessage());
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