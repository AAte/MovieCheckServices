package com.thesquad.microservice.moviesservice.controller;


import com.thesquad.microservice.moviesservice.model.ResponseModel;
import com.thesquad.microservice.moviesservice.model.ValidationModel;
import com.thesquad.microservice.moviesservice.model.entities.Movie;
import com.thesquad.microservice.moviesservice.model.exceptions.MovieNotFoundException;
import com.thesquad.microservice.moviesservice.service.IMovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("api/v1")
public class MoviesController {

    @Autowired
    private IMovieService movieService;

    @GetMapping("/movies/name={title}")
    public ResponseModel<Object> getMovieByName(@PathVariable String title) {
        try {
            Movie movieByTitle = movieService.getMovieByTitle(title);
            movieService.saveMovie(movieByTitle);
            return ResponseModel.builder().data(movieByTitle)
                    .validationModel(ValidationModel.builder().code(201).message("Success").build())
                    .build();

        } catch (Exception e) {
            return ResponseModel.builder().data(null)
                    .validationModel(ValidationModel.builder().code(500).message("Error fetching the movies").build())
                    .build();
        }
    }

    @GetMapping("/movies/id/{id}")
    public ResponseModel<Object> getMovieById(@PathVariable Long id) throws MovieNotFoundException {
        try {
            Movie movies = movieService.findById(id);
            return ResponseModel.builder().data(movies)
                    .validationModel(ValidationModel.builder().code(201).message("Success").build())
                    .build();

        } catch (Exception e) {
            return ResponseModel.builder().data(null)
                    .validationModel(ValidationModel.builder().code(500).message("Error fetching the movies").build())
                    .build();
        }
    }

    @GetMapping("/movies")
    public ResponseModel<Object> getAllMovies() {
        try {
            List<Movie> movies = movieService.findAll();
            return ResponseModel.builder().data(movies)
                    .validationModel(ValidationModel.builder().code(201).message("Success").build())
                    .build();

        } catch (Exception e) {
            return ResponseModel.builder().data(null)
                    .validationModel(ValidationModel.builder().code(500).message("Error fetching the movies").build())
                    .build();
        }
    }

    @PostMapping("/movies/list")
    public ResponseModel<Object> getAllMoviesByImdbIds(@RequestBody List<String> imdbIds) {
        try {
            List<Movie> moviesByImdbIds = movieService.findAllByImdbID(imdbIds);
            return ResponseModel.builder().data(moviesByImdbIds)
                    .validationModel(ValidationModel.builder().code(201).message("Success").build())
                    .build();

        } catch (Exception e) {
            return ResponseModel.builder().data(null)
                    .validationModel(ValidationModel.builder().code(500).message("Error fetching the movies").build())
                    .build();
        }
    }

    @GetMapping("/movies/imdbId/{imdbId}")
    public ResponseModel<Object> getMovieByImdbId(@PathVariable String imdbId) throws MovieNotFoundException {
        try {
            Movie movieByImdbId = movieService.findByImdbId(imdbId);
            return ResponseModel.builder().data(movieByImdbId)
                    .validationModel(ValidationModel.builder().code(201).message("Success").build())
                    .build();

        } catch (Exception e) {
            return ResponseModel.builder().data(null)
                    .validationModel(ValidationModel.builder().code(500).message("Error fetching the movies").build())
                    .build();
        }
    }
}