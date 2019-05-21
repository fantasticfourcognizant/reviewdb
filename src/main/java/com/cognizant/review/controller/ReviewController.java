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

    @GetMapping("/reviewbymovieid")
    public ResponseEntity<String> getReviewsByMovieId(@RequestParam Long movieId) {
        ObjectMapper mapper = new ObjectMapper();
        List<Review> reviews = reviewService.getReviewsByMovieId(movieId);

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

    @GetMapping("/reviewbyuserid")
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
    public ResponseEntity<String>  addReview(@RequestParam String reviewTitle, @RequestParam String reviewBody, @RequestParam Long movieId, @RequestParam Long userId){

        Review newReview = reviewService.addReview(reviewTitle, reviewBody, movieId, userId);

        if(newReview == null) {
            return new ResponseEntity<>("Error creating review!", HttpStatus.UNPROCESSABLE_ENTITY);
        }
        return new ResponseEntity<>("Review Added!", HttpStatus.CREATED);

    }
    //just stuff


}
