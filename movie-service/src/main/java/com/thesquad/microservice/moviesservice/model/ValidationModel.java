package com.thesquad.microservice.moviesservice.model;

import lombok.*;

/**
 * Generic validation model part of the response model used for
 * REST interface {@link com.thesquad.microservice.moviesservice.controller.MoviesController}
 * Message of the model is in format "Error: {Human readable exception message}"
 * Code is analogous to the HTTP code standard
 *
 * @author Atanas Asprovski
 * @version 1.0
 */
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Builder
public class ValidationModel {
    private String message;
    private int code;

}
