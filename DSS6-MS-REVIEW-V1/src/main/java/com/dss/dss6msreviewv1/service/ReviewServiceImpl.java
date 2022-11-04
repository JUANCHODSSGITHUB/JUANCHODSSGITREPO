package com.dss.dss6msreviewv1.service;

import com.dss.dss6msreviewv1.dto.ReviewDTO;
import com.dss.dss6msreviewv1.dto.ReviewDTOMapper;
import com.dss.dss6msreviewv1.entity.Review;
import com.dss.dss6msreviewv1.repository.ReviewRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class ReviewServiceImpl implements ReviewService{

    private static final Logger LOG =   LoggerFactory.getLogger(ReviewServiceImpl.class);

    @Autowired
    ReviewRepository reviewRepository;

    @Override
    public Review addReview(ReviewDTO reviewDTO) {
        ReviewDTOMapper reviewDTOMapper = new ReviewDTOMapper();
        Review movie = reviewDTOMapper.mapReview(reviewDTO);
        Review newMovie = reviewRepository.save(movie);
        return newMovie;
    }

    @Override
    public String deleteReview(int id) {
        String responseMessage = null;

        try {
            reviewRepository.deleteById(id);
            responseMessage = "Data successfully deleted.";

        }catch(EmptyResultDataAccessException | NoSuchElementException e){
            responseMessage = "No such data with id = " + id + ".";
            LOG.error(e.getMessage());
        }
        return responseMessage;
    }

    @Override
    public List<Review> viewReviewsByMovieId(int movieId) {
        List<Review> reviewFound = null;
        try{
            reviewFound = reviewRepository.findByMovieId(movieId);
            reviewFound.size(); //for checking
        }catch (NoSuchElementException | NullPointerException e){
            LOG.error(e.getMessage());
        }
        return reviewFound;
    }

    @Override
    public Review viewReviewById(int reviewId) {
        Review reviewFound = null;

        try{
            Optional<Review> review = reviewRepository.findById(reviewId);
            reviewFound = review.get();
        }catch (NoSuchElementException | NullPointerException e){
            LOG.error(e.getMessage());
        }

        return reviewFound;
    }

    @Override
    public List<Review> viewAllReviews() {
        List<Review> reviewList = null;
        reviewList = reviewRepository.findAll();
        return reviewList;
    }

    @Override
    public String updateReview(int id, ReviewDTO reviewDTO) {
        String responseMessage = null;
        Review reviewFound = null;
        ReviewDTOMapper reviewDTOMapper = new ReviewDTOMapper();

        try{
            Optional<Review> movie = reviewRepository.findById(id);
            reviewFound = movie.get();
            Review reviewUpdate = reviewDTOMapper.mapReview(reviewDTO);
            reviewFound.setDescription(reviewUpdate.getDescription());
            reviewFound.setRating(reviewUpdate.getRating());
            reviewFound.setMovieId(reviewUpdate.getMovieId());
            reviewFound.setDatePosted(reviewUpdate.getDatePosted());
            reviewRepository.save(reviewFound);
            responseMessage = "Data successfully updated.";

        }catch (NoSuchElementException | NullPointerException e){
            responseMessage = "No such data with id = " +id + ".";
            LOG.error(e.getMessage());
        }
        return responseMessage;
    }
}
