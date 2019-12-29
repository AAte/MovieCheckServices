package com.thesquad.microservice.listservice.peristence;

import com.thesquad.microservice.listservice.model.MovieBasicInfo;
import com.thesquad.microservice.listservice.model.MovieList;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface MovieBasicInfoRepository extends JpaRepository<MovieBasicInfo, Long> {

    Optional<MovieBasicInfo> findByImdbIdAndAndMovieList(String imdbId, MovieList movieList);
}
