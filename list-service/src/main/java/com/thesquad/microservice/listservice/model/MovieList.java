package com.thesquad.microservice.listservice.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.BatchSize;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class MovieList {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @Size(min = 2, message = "Type should be atlest 2 characters")
    private String type;
    private String userEmail;
    @JsonIgnore
    @OneToMany(cascade = {CascadeType.ALL}, fetch = FetchType.LAZY, mappedBy = "movieList")
    @BatchSize(size = 10)
    private List<MovieBasicInfo> movies;
    private Date dateCreated;
    private Date dateModified;
}
