package com.thesquad.microservice.listservice.model.exceptions;
/**
 * The class is a custom exception for use in the {@link com.thesquad.microservice.listservice.service.implementation.MovieListService}
 *
 * @version 1.0
 */
public class MovieListNotFoundException extends Exception {
    public MovieListNotFoundException() {
    }

    public MovieListNotFoundException(String message) {
        super(message);
    }
}
