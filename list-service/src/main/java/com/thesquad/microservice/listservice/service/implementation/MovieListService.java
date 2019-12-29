package com.thesquad.microservice.listservice.service.implementation;

import com.thesquad.microservice.listservice.model.MovieBasicInfo;
import com.thesquad.microservice.listservice.model.MovieList;
import com.thesquad.microservice.listservice.model.exceptions.MovieListNotFoundException;
import com.thesquad.microservice.listservice.model.exceptions.MovieNotFoundInMovieListException;
import com.thesquad.microservice.listservice.peristence.MovieBasicInfoRepository;
import com.thesquad.microservice.listservice.peristence.MovieListRepository;
import com.thesquad.microservice.listservice.service.IMovieListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class MovieListService implements IMovieListService {

    @Autowired
    MovieListRepository movieListRepository;

    @Autowired
    MovieBasicInfoRepository movieBasicInfoRepository;

    @Override
    public void saveMovieList(MovieList movieList) {
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

}
