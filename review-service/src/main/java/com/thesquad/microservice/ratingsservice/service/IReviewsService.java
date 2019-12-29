package com.thesquad.microservice.ratingsservice.service;


import com.thesquad.microservice.ratingsservice.model.entities.Review;
import com.thesquad.microservice.ratingsservice.model.exceptions.ReviewNotFoundException;


public interface IReviewsService {


    void saveReview(Review review);

    Review findReviewById(long id) throws ReviewNotFoundException;
}
