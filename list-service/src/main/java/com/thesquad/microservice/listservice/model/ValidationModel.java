package com.thesquad.microservice.listservice.model;

import com.thesquad.microservice.listservice.controller.MovieListController;
import lombok.*;

/**
 * Generic validation model part of the response model used for
 * REST interface {@link MovieListController}
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
