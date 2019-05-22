package com.cognizant.review.service;

import com.cognizant.review.model.Review;
import com.cognizant.review.repository.ReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

@Service
public class ReviewService {

    private final ReviewRepository reviewRepository;

    @Autowired
    public ReviewService(ReviewRepository reviewRepository){
       this.reviewRepository = reviewRepository;
    }


    public Review addReview(Review review){
        Review newReview = new Review();
        newReview.setReviewText(review.getReviewText());
        newReview.setReviewTitle(review.getReviewTitle());
        newReview.setMovieId(review.getMovieId());
        newReview.setUserId(review.getUserId());
        newReview.setLastUpdated(new Date());

        return reviewRepository.save(newReview);
    }

    public List<Review> getReviewsByMovieId(Long movieId) {
        List<Review> reviews = reviewRepository.findReviewsByMovieId(movieId);
        if(reviews == null) { return null; }
        return reviews;
    }

    public List<Review> getReviewsByUserId(Long userId) {
        List<Review> reviews = reviewRepository.findReviewsByUserId(userId);
        if(reviews == null) { return null; }
        return reviews;
    }
}
