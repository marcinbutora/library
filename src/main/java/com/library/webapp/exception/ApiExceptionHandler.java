package com.library.webapp.exception;

import com.library.webapp.person.PersonNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDateTime;

@ControllerAdvice
public class ApiExceptionHandler {

    @ExceptionHandler(value = {PersonNotFoundException.class})
    ResponseEntity<ApiException> handlePersonNotFoundException(PersonNotFoundException exception, WebRequest request) {
        ApiException apiException = new ApiException(LocalDateTime.now(), exception.getMessage(), request.getDescription(false));
                return new ResponseEntity<>(apiException, HttpStatus.NOT_FOUND);
    }
}
