package com.dss.dss6msreviewv1.service;

import com.dss.dss6msreviewv1.dto.ReviewDTO;
import com.dss.dss6msreviewv1.dto.ReviewDTOMapper;
import com.dss.dss6msreviewv1.entity.Review;
import com.dss.dss6msreviewv1.exception.MovieNotFoundException;
import com.dss.dss6msreviewv1.exception.ReviewNotFoundException;
import com.dss.dss6msreviewv1.repository.ReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ReviewServiceImpl implements ReviewService{

    @Autowired
    ReviewRepository reviewRepository;

    @Override
    public Review addReview(ReviewDTO reviewDTO) {
        ReviewDTOMapper reviewDTOMapper = new ReviewDTOMapper();
        Review movie = reviewDTOMapper.mapReview(reviewDTO);
        try{
            return reviewRepository.save(movie);
        }catch (Exception e){
            throw new MovieNotFoundException("No movie found with id: " + movie.getMovieId());
        }
    }

    @Override
    public String deleteReview(int id) {
        String responseMessage = null;
        Optional<Review> review = reviewRepository.findById(id);
        if(review.isPresent()) {
            reviewRepository.deleteById(id);
            responseMessage = "Data successfully deleted.";
            return responseMessage;
        }else {
            responseMessage = "No such data with id = " + id + ".";
            throw new ReviewNotFoundException(responseMessage);
        }
    }

    @Override
    public List<Review> viewReviewsByMovieId(int movieId) {
        List<Review> reviewFound = null;

        reviewFound = reviewRepository.findByMovieId(movieId);

        if(!reviewFound.isEmpty()){
            return reviewFound;
        }else{
            throw new MovieNotFoundException("No reviews found with movie id: " + movieId + ".");
        }


    }

    @Override
    public Review viewReviewById(int reviewId) {
        Review reviewFound = null;

        Optional<Review> review = reviewRepository.findById(reviewId);
        if(review.isPresent()) {
            reviewFound = review.get();
            return reviewFound;
        }else{
            throw new ReviewNotFoundException("No reviews found with id: " + reviewId + ".");
        }
    }

    @Override
    public List<Review> viewAllReviews() {
        List<Review> reviewList = null;
        reviewList = reviewRepository.findAll();

        if(!reviewList.isEmpty()){
            return reviewList;
        }else {
            throw new ReviewNotFoundException("No reviews found.");
        }

    }

    @Override
    public String updateReview(int id, ReviewDTO reviewDTO) {
        String responseMessage = null;
        Review reviewFound = null;

        Optional<Review> movie = reviewRepository.findById(id);
        if(movie.isPresent()){
            try {
                reviewFound = movie.get();
                reviewFound.setDescription(reviewDTO.getDescription());
                reviewFound.setRating(reviewDTO.getRating());
                reviewFound.setMovieId(reviewDTO.getMovieId());
                reviewFound.setDatePosted(reviewDTO.getDatePosted());
                reviewRepository.save(reviewFound);
                responseMessage = "Data successfully updated.";
                return responseMessage;
            }catch(Exception e){
                throw new MovieNotFoundException("No movie found with id: " + reviewDTO.getMovieId());
            }
        }else {
            responseMessage = "No such data with id = " + id + ".";
            throw new ReviewNotFoundException(responseMessage);
        }

    }
}
