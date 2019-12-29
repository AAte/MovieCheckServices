package com.thesquad.microservice.reviewservice.service;


import com.thesquad.microservice.reviewservice.model.entities.Review;
import com.thesquad.microservice.reviewservice.model.exceptions.ReviewNotFoundException;

import java.util.List;


public interface IReviewsService {


    void saveReview(Review review);

    Review findReviewById(long id) throws ReviewNotFoundException;

    List<Review> findAllByImdbId(String imdbId);

    List<Review> findAllByMovieName(String movieName);

    List<Review> findAllByUserEmail(String userEmail);

    String getUserEmail();
}
