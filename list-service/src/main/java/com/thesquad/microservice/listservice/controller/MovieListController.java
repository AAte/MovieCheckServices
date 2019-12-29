package com.thesquad.microservice.listservice.controller;

import com.thesquad.microservice.listservice.client.MovieServiceProxy;
import com.thesquad.microservice.listservice.model.MovieBasicInfo;
import com.thesquad.microservice.listservice.model.MovieList;
import com.thesquad.microservice.listservice.model.ResponseModel;
import com.thesquad.microservice.listservice.model.ValidationModel;
import com.thesquad.microservice.listservice.model.exceptions.MovieListNotFoundException;
import com.thesquad.microservice.listservice.model.json.Movie;
import com.thesquad.microservice.listservice.model.json.MovieListEnriched;
import com.thesquad.microservice.listservice.service.IMovieListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("api/v1")
public class MovieListController {

    @Autowired
    MovieServiceProxy movieServiceProxy;
    @Autowired
    IMovieListService movieListService;


    @GetMapping("/lists/test")
    public String movieTest() {
       String email = movieListService.getUserEmail();
        return movieServiceProxy.feignTest();
    }

    @GetMapping("/movieLists/imdbId/{imdbId}")
    public ResponseModel<Movie> getMovieByImdbId(@PathVariable String imdbId) {
        return movieServiceProxy.getMovieByImdbId(imdbId);
    }

    @GetMapping("/lists/all")
    public ResponseModel<Object> getEveryMovieList(){

        try {
            List<MovieList> everyMovieListByUser = movieListService.findEveryMovieListByUser(movieListService.getUserEmail());
            List<MovieListEnriched> everyMovieListEnriched= new ArrayList<>();
            for (MovieList movieList:everyMovieListByUser) {
                List<String> imdbIds = new ArrayList<>();
                movieList.getMovies().forEach(movieBasicInfo -> imdbIds.add(movieBasicInfo.getImdbId()));
                ResponseModel<List<Movie>> listResponseModel = movieServiceProxy.getAllMoviesByImdbIds(imdbIds);
                MovieListEnriched movieListEnriched = MovieListEnriched.builder().id(movieList.getId()).type(movieList.getType()).dateCreated(movieList.getDateCreated()).movies(listResponseModel.getData()).build();
                everyMovieListEnriched.add(movieListEnriched);
            }
            return ResponseModel.builder().data(everyMovieListEnriched)
                    .validationModel(ValidationModel.builder().code(201).message("Success").build())
                    .build();
        } catch (Exception e) {
            return ResponseModel.builder().data(null)
                    .validationModel(ValidationModel.builder().code(500).message("Error fetching the movies").build())
                    .build();
        }
    }
    @GetMapping("/lists/{type}")
    public ResponseModel<Object> getEveryMovieList(@PathVariable String type){

        try {
                MovieList movieList = movieListService.findMovieListByUserAndType(type,movieListService.getUserEmail());
                if(movieList==null){
                    return ResponseModel.builder().data(null)
                            .validationModel(ValidationModel.builder().code(500).message("No movie list found of type: " + type).build())
                            .build();
                }
                List<String> imdbIds = new ArrayList<>();
                movieList.getMovies().forEach(movieBasicInfo -> imdbIds.add(movieBasicInfo.getImdbId()));
                ResponseModel<List<Movie>> listResponseModel = movieServiceProxy.getAllMoviesByImdbIds(imdbIds);
                MovieListEnriched movieListEnriched = MovieListEnriched.builder().id(movieList.getId()).type(movieList.getType()).dateCreated(movieList.getDateCreated()).movies(listResponseModel.getData()).build();
            return ResponseModel.builder().data(movieListEnriched)
                    .validationModel(ValidationModel.builder().code(201).message("Success").build())
                    .build();
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseModel.builder().data(null)
                    .validationModel(ValidationModel.builder().code(500).message("Error fetching the movies").build())
                    .build();
        }
    }


    @PostMapping("/lists/new")
    public ResponseModel<Object> createMovieList(@Valid @RequestBody MovieList movieList) {
        try {
            movieList.setUserEmail(movieListService.getUserEmail());
            MovieList movieListByUser = movieListService.findMovieListByUserAndType(movieList.getType(),movieList.getUserEmail());
            if(movieListByUser==null){
                movieList.setDateCreated(new Date());
                movieListService.saveMovieList(movieList);
                MovieListEnriched movieListEnriched = MovieListEnriched.builder().id(movieList.getId()).type(movieList.getType()).dateCreated(movieList.getDateCreated()).movies(null).build();
                return ResponseModel.builder().data(movieListEnriched)
                        .validationModel(ValidationModel.builder().code(201).message("Success").build())
                        .build();
            }else{
                return ResponseModel.builder().data(movieList)
                        .validationModel(ValidationModel.builder().code(500).message("List already exists").build())
                        .build();
            }
        } catch (Exception e) {
            return ResponseModel.builder().data(null)
                    .validationModel(ValidationModel.builder().code(500).message("Error fetching the movies").build())
                    .build();
        }
    }

    @PostMapping("/lists/{type}/add/imdbId/{imdbId}")
    public ResponseModel<Object>  addToMovieList(@PathVariable String type, @PathVariable String imdbId){
        try {
            MovieList movieList = movieListService.findMovieListByUserAndType(type,movieListService.getUserEmail());
            ResponseModel<Movie> movieResponseModel = movieServiceProxy.getMovieByImdbId(imdbId);
            Movie movie = movieResponseModel.getData();
            movieList.getMovies().add(new MovieBasicInfo(movie.getImdbID(), movie.getTitle(), movieList));
            movieListService.saveMovieList(movieList);
            return ResponseModel.builder().data(movieList)
                    .validationModel(ValidationModel.builder().code(201).message("Success").build())
                    .build();
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseModel.builder().data(null)
                    .validationModel(ValidationModel.builder().code(500).message("Error fetching the movies").build())
                    .build();
        }
    }

    @DeleteMapping("/lists/{type}/delete/imdbId/{imdbId}")
    public ResponseModel<Object>  deleteFromMovieList(@PathVariable String type, @PathVariable String imdbId){
        try {
            MovieList movieList = movieListService.findMovieListByUserAndType(type,movieListService.getUserEmail());
            movieListService.removeMovieFromList(movieList,imdbId);
            return ResponseModel.builder().data(movieList)
                    .validationModel(ValidationModel.builder().code(201).message("Success").build())
                    .build();
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseModel.builder().data(null)
                    .validationModel(ValidationModel.builder().code(500).message(e.getMessage()).build())
                    .build();
        }
    }


    @DeleteMapping("/lists/{type}")
    public ResponseModel<Object>  deleteFromMovieList(@PathVariable String type){
        try {
            MovieList movieList = movieListService.findMovieListByUserAndType(type,movieListService.getUserEmail());
            movieListService.deleteMovieList(movieList);
            return ResponseModel.builder().data(null)
                    .validationModel(ValidationModel.builder().code(201).message("Success").build())
                    .build();
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseModel.builder().data(null)
                    .validationModel(ValidationModel.builder().code(500).message("Error while finding the list").build())
                    .build();
        }
    }

}
