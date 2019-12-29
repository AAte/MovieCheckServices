package com.thesquad.microservice.ratingsservice.client;

import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@FeignClient(name = "netfilx-zuul-api-gateway-server")
@RibbonClient(name = "movies-service")
public interface
MovieServiceProxy {

    @GetMapping("movies-service/movies/imdbId/{imdbId}")
    Movie getMovieByImdbId(@PathVariable String imdbId);

    @PostMapping("movies-service/movies/list")
    List<Movie> getAllMoviesByImdbIds(@RequestBody List<String> imdbIds);
}
