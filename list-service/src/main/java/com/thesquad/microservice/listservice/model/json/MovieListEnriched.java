package com.thesquad.microservice.listservice.model.json;

import lombok.Builder;
import lombok.Getter;

import java.util.Date;
import java.util.List;
/**
 * The class is json model used for and enriched list of movies gathered from the movie-service
 *
 * @version 1.0
 */
@Getter
@Builder
public class MovieListEnriched {
    private long id;
    private String type;
    private Date dateCreated ;
    private List<Movie> movies;
}
