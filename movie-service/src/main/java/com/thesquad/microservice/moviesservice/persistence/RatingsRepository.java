package com.thesquad.microservice.moviesservice.persistence;

import com.thesquad.microservice.moviesservice.model.entities.Rating;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RatingsRepository extends JpaRepository<Rating, Long> {

    @Override
    List<Rating> findAll();

    @Override
    Optional<Rating> findById(Long id);
}
