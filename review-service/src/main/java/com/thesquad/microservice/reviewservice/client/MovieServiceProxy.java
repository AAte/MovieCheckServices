package com.thesquad.microservice.reviewservice.client;

import com.thesquad.microservice.reviewservice.model.ResponseModel;
import com.thesquad.microservice.reviewservice.model.json.Movie;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@FeignClient(name = "netfilx-zuul-api-gateway-server")
@RibbonClient(name = "movies-service")
public interface MovieServiceProxy {

    @GetMapping("movies-service/api/v1/movies/hello")
    public String feignTest();

    @GetMapping("movies-service/api/v1/movies/imdbId/{imdbId}")
    ResponseModel<Movie> getMovieByImdbId(@PathVariable String imdbId);

    @PostMapping("movies-service/api/v1/movies/list")
    ResponseModel<List<Movie>> getAllMoviesByImdbIds(@RequestBody List<String> imdbIds);
}
