package com.thesquad.microservice.ratingsservice.controller;

import com.thesquad.microservice.ratingsservice.client.MovieServiceProxy;
import com.thesquad.microservice.ratingsservice.model.entities.Review;
import com.thesquad.microservice.ratingsservice.service.IReviewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
public class ReviewsController {

    @Autowired
    MovieServiceProxy movieServiceProxy;
    @Autowired
    IReviewsService reviewsService;

    @GetMapping("/reviews/movie/imdbId/{imdbId}")
    public Movie getMovieByImdbId(@PathVariable String imdbId) {
        Movie movie = movieServiceProxy.getMovieByImdbId(imdbId);
        return movie;
    }

    @PostMapping("/reviews/new")
    public Review createReview(@Valid @RequestBody Review review) {
        reviewsService.saveReview(review);
        return review;
    }


}
