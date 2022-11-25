package com.dss.dss6msreviewv1.controller;


import com.dss.dss6msreviewv1.dto.ReviewDTO;
import com.dss.dss6msreviewv1.entity.Review;
import com.dss.dss6msreviewv1.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/dss/api")
public class ReviewController {

    @Autowired
    private ReviewService reviewService;

    @GetMapping("/review/{id}")
    public ResponseEntity<Review> getReview(@PathVariable int id){

        return new ResponseEntity<>(reviewService.viewReviewById(id), HttpStatus.FOUND);
    }

    @GetMapping("/review")
    public ResponseEntity<List<Review>> getReviews(){
        return new ResponseEntity<>(reviewService.viewAllReviews(), HttpStatus.FOUND);
    }

    @GetMapping("/review/movie-id/{id}")
    public ResponseEntity<List<Review>> getReviewsByMovieId(@PathVariable int id){
        return new ResponseEntity<>(reviewService.viewReviewsByMovieId(id), HttpStatus.FOUND);
    }

    @DeleteMapping("/review/{id}")
    public ResponseEntity<String> deleteReview(@PathVariable int id){
        return new ResponseEntity<>(reviewService.deleteReview(id), HttpStatus.OK);
    }

    @PostMapping("/review")
    public ResponseEntity<Review> insertReview(@RequestBody ReviewDTO reviewDTO){
        return new ResponseEntity<>(reviewService.addReview(reviewDTO),HttpStatus.CREATED);
    }

    @PutMapping("/review/{id}")
    public ResponseEntity<String> updateReview(@PathVariable int id, @RequestBody ReviewDTO reviewDTO){
        return new ResponseEntity<>(reviewService.updateReview(id, reviewDTO),HttpStatus.OK);
    }


}
