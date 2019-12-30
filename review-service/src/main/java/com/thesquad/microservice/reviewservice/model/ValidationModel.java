package com.thesquad.microservice.reviewservice.model;

import com.thesquad.microservice.reviewservice.controller.ReviewsController;
import lombok.*;

/**
 * Generic validation model part of the response model used for
 * REST interface {@link ReviewsController}
 * Message of the model is in format "Error: {Human readable exception message}"
 * Code is analogous to the HTTP code standard
 *
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
