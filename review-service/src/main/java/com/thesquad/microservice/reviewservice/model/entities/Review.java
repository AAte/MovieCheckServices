package com.thesquad.microservice.reviewservice.model.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Range;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;


@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
public class Review {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @JsonIgnore
    private long id;
    @NotNull
    @Range(min = 1, max = 10)
    private int score;
    @NotNull
    @Size(min = 10, message = "Comment is too short")
    private String comment;
    private String userEmail;
    @NotNull
    private String imdbId;
    @NotNull
    private String movieName;
    @JsonIgnore
    private Date dateEntered;
    @JsonIgnore
    private Date dateModified;
}
