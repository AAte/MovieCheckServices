package com.thesquad.microservice.listservice.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
public class MovieBasicInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String imdbId;
    private String title;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "movielist_id", nullable = false)
    @JsonIgnore
    private MovieList movieList;

    public MovieBasicInfo(String imdbID, String title, MovieList movieList) {
        this.imdbId = imdbID;
        this.title = title;
        this.movieList = movieList;
    }
}
