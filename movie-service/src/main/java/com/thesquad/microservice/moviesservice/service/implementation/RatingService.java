package com.thesquad.microservice.moviesservice.service.implementation;

import com.thesquad.microservice.moviesservice.persistence.RatingsRepository;
import com.thesquad.microservice.moviesservice.service.IRatingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RatingService implements IRatingService {

    @Autowired
    private RatingsRepository ratingsRepository;

}
