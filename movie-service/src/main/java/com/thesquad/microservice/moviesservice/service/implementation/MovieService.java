package com.thesquad.microservice.moviesservice.service.implementation;

import com.thesquad.microservice.moviesservice.client.OmdbClient;
import com.thesquad.microservice.moviesservice.model.entities.Movie;
import com.thesquad.microservice.moviesservice.model.exceptions.MovieNotFoundException;
import com.thesquad.microservice.moviesservice.persistence.MoviesRepository;
import com.thesquad.microservice.moviesservice.service.IMovieService;
import com.thesquad.microservice.moviesservice.utility.JsonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MovieService implements IMovieService {

    @Autowired
    private MoviesRepository moviesRepository;

    @Autowired
    private OmdbClient omdbClient;

   @Autowired
    private JsonUtils jsonUtils;

    @Override
    public Movie getMovieByTitle(String title) {
        String movieJsonModel = omdbClient.getMovie(title);
        Movie movie = jsonUtils.jsonConverter(movieJsonModel,Movie.class);
        saveMovie(movie);
        return movie;
    }

    @Async
    @Override
    public void saveMovie(Movie movie) {
        if (moviesRepository.findByImdbID(movie.getImdbID()).isEmpty()) {
            movie.getRatings().parallelStream().forEach(r -> r.setMovie(movie));
            moviesRepository.save(movie);
        }
    }

    @Override
    public void deleteMovie(Movie movie) {
        moviesRepository.delete(movie);
    }


    @Override
    public Movie findById(Long id) throws MovieNotFoundException {

        Optional<Movie> movieOptional = moviesRepository.findById(id);
        if (movieOptional.isPresent()) {
            return movieOptional.get();
        } else {
            throw new MovieNotFoundException("Movie id: " + id);
        }

    }

    @Override
    public List<Movie> findAll() {
        return moviesRepository.findAll();
    }

    @Override
    public List<Movie> findAllByImdbID(List<String> imdbIds) {
        return moviesRepository.findByImdbIDIn(imdbIds);
    }

    @Override
    public Movie findByImdbId(String imdbId) throws MovieNotFoundException {
        Optional<Movie> movieOptional = moviesRepository.findByImdbID(imdbId);
        if (movieOptional.isPresent()) {
            return movieOptional.get();
        } else {
            throw new MovieNotFoundException("Movie imdbId: " + imdbId);
        }
    }
}
