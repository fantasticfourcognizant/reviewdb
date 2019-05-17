package com.cognizant.review.service;

import com.cognizant.review.model.Review;
import com.cognizant.review.repository.ReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

@Service
public class ReviewService {

    private final ReviewRepository reviewRepository;

    @Autowired
    public ReviewService(ReviewRepository reviewRepository){
       this.reviewRepository = reviewRepository;
    }


    public Review addReview(Review review){
        return reviewRepository.save(review);
    }
}
