package com.thesquad.microservice.listservice.model.json;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Getter
@Builder
public class MovieListEnriched {
    private long id;
    private String type;
    private Date dateCreated ;
    private List<Movie> movies;
}
