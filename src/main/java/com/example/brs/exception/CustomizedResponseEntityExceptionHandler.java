package com.example.brs.exception;


import com.example.brs.dto.mapper.response.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
@RestController
public class CustomizedResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler(BRSException.EntityNotFoundException.class)
    public final ResponseEntity<?> handleNotFoundException(Exception exception, WebRequest request) {
        Response<?> response = Response.notFound();
        response.addErrorMessageToResponse(exception.getMessage(), exception);
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(BRSException.DuplicateEntityException.class)
    public final ResponseEntity<?> handleDuplicateEntityException(Exception exception, WebRequest request) {
        Response<?> response = Response.duplicateEntity();
        response.addErrorMessageToResponse(exception.getMessage(), exception);
        return new ResponseEntity<>(response, HttpStatus.CONFLICT);
    }
}
