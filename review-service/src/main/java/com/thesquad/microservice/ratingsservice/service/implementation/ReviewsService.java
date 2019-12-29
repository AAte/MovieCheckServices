package com.thesquad.microservice.ratingsservice.service.implementation;

import com.thesquad.microservice.ratingsservice.model.entities.Review;
import com.thesquad.microservice.ratingsservice.model.exceptions.ReviewNotFoundException;
import com.thesquad.microservice.ratingsservice.persistence.ReviewsRepository;
import com.thesquad.microservice.ratingsservice.service.IReviewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ReviewsService implements IReviewsService {

    @Autowired
    ReviewsRepository reviewsRepository;

    @Override
    public void saveReview(Review review) {
        reviewsRepository.save(review);
    }

    @Override
    public Review findReviewById(long id) throws ReviewNotFoundException {

        Optional<Review> reviewOptional = reviewsRepository.findById(id);

        if (reviewOptional.isPresent()) {
            return reviewOptional.get();
        } else {
            throw new ReviewNotFoundException("id: " + id);
        }

    }

}
