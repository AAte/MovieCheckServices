package com.thesquad.microservice.reviewservice.model;

import lombok.*;

/**
 * Generic response model used for REST interface {@link  ISmsService}
 *
 * @param <T> - The entity that will be returned in the response body of the request
 * @version 1.0
 */
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Builder
public class ResponseModel<T> {
    private T data;
    private ValidationModel validationModel;
}