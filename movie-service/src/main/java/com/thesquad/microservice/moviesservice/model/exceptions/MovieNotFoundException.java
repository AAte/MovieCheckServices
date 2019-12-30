package com.thesquad.microservice.moviesservice.model.exceptions;

/**
 * The class is a custom exception for use in the {@link com.thesquad.microservice.moviesservice.controller.MoviesController}
 *
 * @version 1.0
 */
public class MovieNotFoundException extends Exception {
    public MovieNotFoundException() {
        super();
    }

    public MovieNotFoundException(String message) {
        super(message);
    }
}
