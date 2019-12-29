package com.thesquad.microservice.ratingsservice.model.exceptions;

public class ReviewNotFoundException extends Exception {
    public ReviewNotFoundException() {
        super();
    }

    public ReviewNotFoundException(String message) {
        super(message);
    }
}
