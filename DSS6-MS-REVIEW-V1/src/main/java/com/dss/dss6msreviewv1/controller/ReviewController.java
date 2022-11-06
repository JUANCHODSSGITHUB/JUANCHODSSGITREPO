package com.dss.dss6msreviewv1.controller;


import com.dss.dss6msreviewv1.dto.ReviewDTO;
import com.dss.dss6msreviewv1.entity.Review;
import com.dss.dss6msreviewv1.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/dss/api")
public class ReviewController {

    @Autowired
    private ReviewService reviewService;

    @GetMapping("/review/{id}")
    public Review getReview(@PathVariable int id){
       Review Review = reviewService.viewReviewById(id);
        return Review;
    }

    @GetMapping("/review")
    public List<Review> getReviews(){
        List reviewList = reviewService.viewAllReviews();
        return reviewList;
    }

    @GetMapping("/review/movie-id/{id}")
    public List<Review> getReviewsByMovieId(@PathVariable int id){
        List reviewList = reviewService.viewReviewsByMovieId(id);
        return reviewList;
    }

    @DeleteMapping("/review/{id}")
    public String deleteReview(@PathVariable int id){
        return reviewService.deleteReview(id);
    }

    @PostMapping("/review")
    public Review insertReview(@RequestBody ReviewDTO reviewDTO){
        return reviewService.addReview(reviewDTO);
    }

    @PutMapping("/review/{id}")
    public String updateReview(@PathVariable int id, @RequestBody ReviewDTO reviewDTO){
        return reviewService.updateReview(id, reviewDTO);
    }


}
