package com.thesquad.microservice.listservice.peristence;

import com.thesquad.microservice.listservice.model.MovieList;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
/**
 * A repository class for the {@link MovieList} entity
 *
 * @version 1.0
 */
public interface MovieListRepository extends JpaRepository<MovieList, Long> {

    List<MovieList> findByUserEmail(String userEmail);
    Optional<MovieList> findByTypeIgnoreCaseAndUserEmail(String type,String userEmail);
}
