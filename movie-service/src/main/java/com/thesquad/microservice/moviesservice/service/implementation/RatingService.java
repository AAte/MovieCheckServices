package com.thesquad.microservice.moviesservice.service.implementation;

import com.thesquad.microservice.moviesservice.persistence.RatingsRepository;
import com.thesquad.microservice.moviesservice.service.IRatingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
/**
 * A service class that extends the  {@link IRatingService}
 *
 * @version 1.0
 */
@Service
public class RatingService implements IRatingService {

    @Autowired
    private RatingsRepository ratingsRepository;

}
