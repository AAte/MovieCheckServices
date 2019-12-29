package com.thesquad.microservice.listservice.service;

import com.thesquad.microservice.listservice.model.MovieBasicInfo;
import com.thesquad.microservice.listservice.model.MovieList;
import com.thesquad.microservice.listservice.model.exceptions.MovieListNotFoundException;
import com.thesquad.microservice.listservice.model.exceptions.MovieNotFoundInMovieListException;

import java.util.List;


public interface IMovieListService {

    void saveMovieList(MovieList movieList);

    void removeMovieFromList(MovieList movieList, String imdbId) throws MovieNotFoundInMovieListException;

    void deleteMovieList(MovieList movieList);

    MovieList findMovieListById(long id) throws MovieListNotFoundException;

    List<MovieList> findEveryMovieListByUser(String userEmail) throws MovieListNotFoundException;

    MovieList findMovieListByUserAndType(String type , String userEmail);

    String getUserEmail();
}
