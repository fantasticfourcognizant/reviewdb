package com.cognizant.review.repository;

import com.cognizant.review.model.Review;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReviewRepository extends JpaRepository<Review, Long> {

  List<Review> findReviewsByMovieId(Long movieId);


    List<Review> findReviewsByUserId(Long userId);
}
