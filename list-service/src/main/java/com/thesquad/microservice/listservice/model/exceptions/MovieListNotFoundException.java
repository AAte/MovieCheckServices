package com.thesquad.microservice.listservice.model.exceptions;

public class MovieListNotFoundException extends Exception {
    public MovieListNotFoundException() {
    }

    public MovieListNotFoundException(String message) {
        super(message);
    }
}
