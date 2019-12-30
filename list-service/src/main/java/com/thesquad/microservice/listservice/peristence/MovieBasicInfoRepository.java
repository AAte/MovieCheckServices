package com.thesquad.microservice.listservice.peristence;

import com.thesquad.microservice.listservice.model.MovieBasicInfo;
import com.thesquad.microservice.listservice.model.MovieList;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
/**
 * A repository class for the {@link MovieBasicInfo} entity
 *
 * @version 1.0
 */
public interface MovieBasicInfoRepository extends JpaRepository<MovieBasicInfo, Long> {

    Optional<MovieBasicInfo> findByImdbIdAndAndMovieList(String imdbId, MovieList movieList);
}
