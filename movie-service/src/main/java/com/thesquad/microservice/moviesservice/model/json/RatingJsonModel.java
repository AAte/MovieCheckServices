package com.thesquad.microservice.moviesservice.model.json;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class RatingJsonModel {

    @JsonProperty("Source")
    public String source;
    @JsonProperty("Value")
    public String value;
}
