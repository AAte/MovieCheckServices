package com.thesquad.microservice.reviewservice.model.exceptions;

import com.thesquad.microservice.reviewservice.controller.ReviewsController;

/**
 * The class is a custom exception for use in the {@link ReviewsController}
 *
 * @version 1.0
 */
public class ReviewNotFoundException extends Exception {
    public ReviewNotFoundException() {
        super();
    }

    public ReviewNotFoundException(String message) {
        super(message);
    }
}
