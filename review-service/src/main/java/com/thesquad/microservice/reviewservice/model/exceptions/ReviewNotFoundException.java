package com.thesquad.microservice.reviewservice.model.exceptions;

public class ReviewNotFoundException extends Exception {
    public ReviewNotFoundException() {
        super();
    }

    public ReviewNotFoundException(String message) {
        super(message);
    }
}
