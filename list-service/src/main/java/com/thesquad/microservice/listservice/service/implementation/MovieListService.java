package com.thesquad.microservice.listservice.service.implementation;

import com.thesquad.microservice.listservice.client.MovieServiceProxy;
import com.thesquad.microservice.listservice.model.MovieBasicInfo;
import com.thesquad.microservice.listservice.model.MovieList;
import com.thesquad.microservice.listservice.model.ResponseModel;
import com.thesquad.microservice.listservice.model.exceptions.MovieListNotFoundException;
import com.thesquad.microservice.listservice.model.exceptions.MovieNotFoundInMovieListException;
import com.thesquad.microservice.listservice.model.json.Movie;
import com.thesquad.microservice.listservice.model.json.MovieListEnriched;
import com.thesquad.microservice.listservice.peristence.MovieBasicInfoRepository;
import com.thesquad.microservice.listservice.peristence.MovieListRepository;
import com.thesquad.microservice.listservice.service.IMovieListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class MovieListService implements IMovieListService {

    @Autowired
    MovieListRepository movieListRepository;

    @Autowired
    MovieBasicInfoRepository movieBasicInfoRepository;

    @Autowired
    MovieServiceProxy movieServiceProxy;

    @Override
    public void saveMovieList(MovieList movieList) {
        if(movieList.getDateCreated()==null){
            movieList.setDateCreated(new Date());
        }
        movieList.setDateModified(new Date());
        movieListRepository.save(movieList);
    }

    @Override
    public void removeMovieFromList(MovieList movieList, String imdbId) throws MovieNotFoundInMovieListException {
        Optional<MovieBasicInfo> movieBasicInfo = movieBasicInfoRepository.findByImdbIdAndAndMovieList(imdbId, movieList);
        if(movieBasicInfo.isPresent()){
            movieBasicInfoRepository.delete(movieBasicInfo.get());
        }else{
            throw new MovieNotFoundInMovieListException("No movie find in movie list with imdbId: " + imdbId);
        }

    }

    @Override
    public void deleteMovieList(MovieList movieList) {
        movieListRepository.delete(movieList);
    }

    @Override
    public MovieList findMovieListById(long id) throws MovieListNotFoundException {

        Optional<MovieList> movieListOptional = movieListRepository.findById(id);

        if (movieListOptional.isPresent()) {
            return movieListOptional.get();
        } else {
            throw new MovieListNotFoundException("id: " + id);
        }

    }

    @Override
    public List<MovieList> findEveryMovieListByUser(String userEmail) throws MovieListNotFoundException {
        List<MovieList> movieLists = movieListRepository.findByUserEmail(userEmail);
        if(movieLists==null || movieLists.isEmpty()){
            throw new MovieListNotFoundException("No movie lists for that user were found");
        }
        return movieLists;
    }

    @Override
    public MovieList findMovieListByUserAndType(String type , String userEmail){
        Optional<MovieList> movieListOptional = movieListRepository.findByTypeIgnoreCaseAndUserEmail(type,userEmail);
        return movieListOptional.orElse(null);
    }

    @Override
    public String getUserEmail() {
        return SecurityContextHolder.getContext().getAuthentication().getName();
    }

    @Override
    public MovieListEnriched getMovieListEnriched(MovieList movieList) {
        List<String> imdbIds = new ArrayList<>();
        movieList.getMovies().forEach(movieBasicInfo -> imdbIds.add(movieBasicInfo.getImdbId()));
        ResponseModel<List<Movie>> listResponseModel = movieServiceProxy.getAllMoviesByImdbIds(imdbIds);
        return MovieListEnriched.builder().id(movieList.getId()).type(movieList.getType()).dateCreated(movieList.getDateCreated()).movies(listResponseModel.getData()).build();
    }

    @Override
    public MovieList createMovieList(String type){
        MovieList movieList = new MovieList();
        movieList.setType(type);
        movieList.setUserEmail(this.getUserEmail());
        MovieList movieListByUser = this.findMovieListByUserAndType(movieList.getType(),movieList.getUserEmail());
        if(movieListByUser==null){
            this.saveMovieList(movieList);
        }
        return movieList;
    };

}
