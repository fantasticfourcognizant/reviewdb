package com.cognizant.review.controller;

import com.cognizant.review.model.Review;
import com.cognizant.review.repository.ReviewRepository;
import com.cognizant.review.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ReviewController {
    private final ReviewService reviewService;

    @Autowired
    public ReviewController(ReviewService reviewService){
        this.reviewService = reviewService;
    }

    @Autowired
    private ReviewRepository reviewRepository;

    @GetMapping("/allreviews")
    public String getAllReviews() {
//        System.out.println("we are here");
//        return new ResponseEntity<>( HttpStatus.OK);
        return "we are here";
    }

    @PostMapping("/addreview")
    public ResponseEntity<String>  addReview(@RequestBody Review review){

        Review newReview = reviewService.addReview(review);

        if(newReview == null) {
            return new ResponseEntity<>("User Already Exist!", HttpStatus.UNPROCESSABLE_ENTITY);
        }
        return new ResponseEntity<>("User Created!", HttpStatus.CREATED);

    }


}
