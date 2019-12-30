package com.thesquad.microservice.reviewservice.controller;

import com.thesquad.microservice.reviewservice.client.MovieServiceProxy;
import com.thesquad.microservice.reviewservice.model.ResponseModel;
import com.thesquad.microservice.reviewservice.model.ValidationModel;
import com.thesquad.microservice.reviewservice.model.entities.Review;
import com.thesquad.microservice.reviewservice.model.json.Movie;
import com.thesquad.microservice.reviewservice.service.IReviewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
/**
 * The class is the controller for the reviews service
 * It uses the builder of the {@link ResponseModel} for providing the response.
 *
 * @version 1.0
 */
@RestController
@CrossOrigin
@RequestMapping("api/v1")
public class ReviewsController {

    @Autowired
    MovieServiceProxy movieServiceProxy;
    @Autowired
    IReviewsService reviewsService;

    @GetMapping("/reviews/movie/imdbId/{imdbId}")
    public ResponseModel<Object> getMovieByImdbId(@PathVariable String imdbId) {
        Movie movie = movieServiceProxy.getMovieByImdbId(imdbId).getData();
        try {
            return ResponseModel.builder().data(movie)
                    .validationModel(ValidationModel.builder().code(201).message("Success").build())
                    .build();
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseModel.builder().data(movie)
                    .validationModel(ValidationModel.builder().code(500).message("Error saving the review").build())
                    .build();
        }
    }

    @PostMapping("/reviews/new")
    public ResponseModel<Object> createReview(@Valid @RequestBody Review review) {
        try {
            reviewsService.saveReview(review);
            return ResponseModel.builder().data(review)
                    .validationModel(ValidationModel.builder().code(201).message("Success").build())
                    .build();
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseModel.builder().data(review)
                    .validationModel(ValidationModel.builder().code(500).message("Error saving the review").build())
                    .build();
        }
    }

    @GetMapping("/reviews/imdbId/{imdbId}")
    public ResponseModel<Object> getReviewsByImdbId(@PathVariable String imdbId) {
        try {
            List<Review> reviews = reviewsService.findAllByImdbId(imdbId);
            return ResponseModel.builder().data(reviews)
                    .validationModel(ValidationModel.builder().code(201).message("Success").build())
                    .build();
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseModel.builder().data(null)
                    .validationModel(ValidationModel.builder().code(500).message("Error occurred while finding reviews with imdbId: " + imdbId).build())
                    .build();
        }
    }

    @GetMapping("/reviews/movieName/{movieName}")
    public ResponseModel<Object> getReviewsByMovieName(@PathVariable String movieName) {
        try {
            List<Review> reviews = reviewsService.findAllByMovieName(movieName);
            return ResponseModel.builder().data(reviews)
                    .validationModel(ValidationModel.builder().code(201).message("Success").build())
                    .build();
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseModel.builder().data(null)
                    .validationModel(ValidationModel.builder().code(500).message("Error occurred while finding reviews with movie name: " + movieName).build())
                    .build();
        }
    }

    @GetMapping("/reviews/user/all")
    public ResponseModel<Object> getAllReviewsForUser() {
        try {
            List<Review> reviews = reviewsService.findAllByUserEmail(reviewsService.getUserEmail());
            return ResponseModel.builder().data(reviews)
                    .validationModel(ValidationModel.builder().code(201).message("Success").build())
                    .build();
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseModel.builder().data(null)
                    .validationModel(ValidationModel.builder().code(500).message("Error occurred while finding reviews with movie name: " + reviewsService.getUserEmail()).build())
                    .build();
        }
    }


}
