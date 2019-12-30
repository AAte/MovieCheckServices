package com.thesquad.microservice.reviewservice.service.implementation;

import com.thesquad.microservice.reviewservice.model.entities.Review;
import com.thesquad.microservice.reviewservice.model.exceptions.ReviewNotFoundException;
import com.thesquad.microservice.reviewservice.persistence.ReviewsRepository;
import com.thesquad.microservice.reviewservice.service.IReviewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;


/**
 * A service class that extends the  {@link IReviewsService}
 *
 * @version 1.0
 */
@Service
public class ReviewsService implements IReviewsService {

    @Autowired
    ReviewsRepository reviewsRepository;

    @Override
    public void saveReview(Review review) {
        review.setUserEmail(this.getUserEmail());
        if (review.getDateEntered() == null) {
            review.setDateEntered(new Date());
        }
        review.setDateModified(new Date());
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

    @Override
    public List<Review> findAllByImdbId(String imdbId){
        return reviewsRepository.findAllByImdbId(imdbId);
    }

    @Override
    public List<Review> findAllByMovieName(String movieName){
        return reviewsRepository.findAllByMovieNameIgnoreCase(movieName);
    }

    @Override
    public List<Review> findAllByUserEmail(String userEmail){
        return reviewsRepository.findAllByUserEmail(userEmail);
    }

    @Override
    public String getUserEmail() {
        return SecurityContextHolder.getContext().getAuthentication().getName();
    }
}
