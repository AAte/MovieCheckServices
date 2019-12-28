package com.thesquad.microservice.moviesservice.model.exceptions;

public class MovieNotFoundException extends Exception {
    public MovieNotFoundException() {
        super();
    }

    public MovieNotFoundException(String message) {
        super(message);
    }
}
