package com.thesquad.microservice.reviewservice.persistence;


import com.thesquad.microservice.reviewservice.model.entities.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ReviewsRepository extends JpaRepository<Review, Long> {

    @Override
    List<Review> findAll();

    @Override
    Optional<Review> findById(Long id);

    List<Review> findAllByImdbId(String imdbId);

    List<Review> findAllByMovieNameIgnoreCase(String movieName);

    List<Review> findAllByUserEmail(String userEmail);

}
