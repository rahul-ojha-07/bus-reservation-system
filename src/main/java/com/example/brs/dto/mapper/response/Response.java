package com.example.brs.dto.mapper.response;


import com.example.brs.util.DateUtils;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class Response<T> {
    private Status status;
    private T payload;
    private Object errors;
    private Object metadata;

    public static <T> Response<T> badRequest() {
        return new Response<T>().setStatus(Status.BAD_REQUEST);
    }

    public static <T> Response<T> ok() {
        return new Response<T>().setStatus(Status.OK);
    }

    public static <T> Response<T> unauthorized() {
        return new Response<T>().setStatus(Status.UNAUTHORIZED);
    }

    public static <T> Response<T> validationException() {
        return new Response<T>().setStatus(Status.VALIDATION_EXCEPTION);
    }

    public static <T> Response<T> wrongCredentials() {
        return new Response<T>().setStatus(Status.WRONG_CREDENTIALS);
    }

    public static <T> Response<T> accessDenied() {
        return new Response<T>().setStatus(Status.ACCESS_DENIED);
    }

    public static <T> Response<T> exception() {
        return new Response<T>().setStatus(Status.EXCEPTION);
    }

    public static <T> Response<T> notFound() {
        return new Response<T>().setStatus(Status.NOT_FOUND);
    }

    public static <T> Response<T> duplicateEntity() {
        return new Response<T>().setStatus(Status.DUPLICATE_ENTITY);
    }

    public void addErrorMessageToResponse(String errorMessage, Exception ex) {
        ResponseError error = ResponseError.builder()
                .withDetails(errorMessage)
                .withMessage(ex.getMessage())
                .withTimestamp(DateUtils.today())
                .build();
        setErrors(error);
    }

    public enum Status {
        OK, BAD_REQUEST, UNAUTHORIZED, VALIDATION_EXCEPTION, EXCEPTION, WRONG_CREDENTIALS, ACCESS_DENIED, NOT_FOUND, DUPLICATE_ENTITY
    }


    @Data
    @Accessors(chain = true)
    @AllArgsConstructor
    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class PageMetaData {
        private final int size;
        private final long totalElements;
        private final int totalPages;
        private final int number;

    }
}


