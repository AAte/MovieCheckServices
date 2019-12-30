package com.thesquad.microservice.moviesservice.persistence;

import com.thesquad.microservice.moviesservice.model.entities.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
/**
 * A repository class for the {@link Movie} entity
 *
 * @version 1.0
 */
@Repository
public interface MoviesRepository extends JpaRepository<Movie, Long> {

    @Override
    List<Movie> findAll();

    @Override
    Optional<Movie> findById(Long id);

    Optional<Movie> findByImdbID(String imdbId);

    @Override
    List<Movie> findAllById(Iterable<Long> iterable);

    List<Movie> findByImdbIDIn(List<String> imdbIds);
}
