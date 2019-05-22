package com.cognizant.review.controller;

import com.cognizant.review.model.Review;
import com.cognizant.review.repository.ReviewRepository;
import com.cognizant.review.service.ReviewService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin
public class ReviewController {
    private final ReviewService reviewService;

    @Autowired
    public ReviewController(ReviewService reviewService){
        this.reviewService = reviewService;
    }

    @Autowired
    private ReviewRepository reviewRepository;

    @GetMapping("/allreviews")
    public List<Review> getAllReviews() {
        List<Review> reviews = new ArrayList<>();
        reviews = reviewRepository.findAll();
        return reviews;
    }

    @PostMapping("/reviewbymovieid")
    public ResponseEntity<String> getReviewsByMovieId(@RequestBody Review review) {
        ObjectMapper mapper = new ObjectMapper();
        List<Review> reviews = reviewService.getReviewsByMovieId(review);

        String json = new String();
        try{
            json = mapper.writeValueAsString(reviews);
        }catch (Exception  e) {
            e.printStackTrace();
        }

        if(reviews == null) {
            return new ResponseEntity<>("No reviews found", HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(json, HttpStatus.OK);
    }

    @PostMapping("/reviewbyuserid")
    public ResponseEntity<String> getReviewsByUserId(@RequestParam Long userId) {
        ObjectMapper mapper = new ObjectMapper();
        List<Review> reviews = reviewService.getReviewsByUserId(userId);

        String json = new String();
        try{
            json = mapper.writeValueAsString(reviews);
        }catch (Exception  e) {
            e.printStackTrace();
        }

        if(reviews == null) {
            return new ResponseEntity<>("No reviews found", HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(json, HttpStatus.OK);
    }

    @PostMapping("/addreview")
    public ResponseEntity<String>  addReview(@RequestBody Review review){

        Review newReview = reviewService.addReview(review);

        if(newReview == null) {
            return new ResponseEntity<>("Error creating review!", HttpStatus.UNPROCESSABLE_ENTITY);
        }
        return new ResponseEntity<>("Review Added!", HttpStatus.CREATED);

    }

    @DeleteMapping("/deletereview")
    public ResponseEntity deleteMovieReview(@RequestParam Long reviewId) {
        reviewRepository.deleteById(reviewId);

        return new ResponseEntity("Review Deleted!", HttpStatus.OK);
    }


}
