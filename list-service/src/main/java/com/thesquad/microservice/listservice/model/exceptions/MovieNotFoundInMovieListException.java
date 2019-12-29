package com.thesquad.microservice.listservice.model.exceptions;

public class MovieNotFoundInMovieListException extends  Exception {
    public MovieNotFoundInMovieListException() {
    }

    public MovieNotFoundInMovieListException(String message) {
        super(message);
    }
}
