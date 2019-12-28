package com.thesquad.microservice.moviesservice.service;

import com.thesquad.microservice.moviesservice.model.entities.Movie;
import com.thesquad.microservice.moviesservice.model.exceptions.MovieNotFoundException;
import org.springframework.scheduling.annotation.Async;

import java.util.List;


public interface IMovieService {
    Movie getMovieByTitle(String title);

    @Async
    void saveMovie(Movie movie);

    Movie findById(Long id) throws MovieNotFoundException;

    List<Movie> findAll();

    List<Movie> findAllByImdbID(List<String> imdbIds);

    Movie findByImdbId(String imdbId) throws MovieNotFoundException;
}
