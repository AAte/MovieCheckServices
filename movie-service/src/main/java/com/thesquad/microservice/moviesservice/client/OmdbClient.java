package com.thesquad.microservice.moviesservice.client;



import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "OmdbClient", url = "https://omdbapi.com")
public interface OmdbClient {
    @GetMapping(value = "/?apikey=424c7664&t={title}", consumes = MediaType.APPLICATION_JSON_VALUE)
    String getMovie(@PathVariable String title);
}
