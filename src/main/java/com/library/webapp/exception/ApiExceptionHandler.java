package com.library.webapp.exception;

import com.library.webapp.book.BookNotFoundException;
import com.library.webapp.person.PersonNotFoundException;
import com.library.webapp.rental.*;
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

    @ExceptionHandler(value = {BookNotFoundException.class})
    ResponseEntity<ApiException> handleBookNotFoundException(BookNotFoundException exception, WebRequest request) {
        ApiException apiException = new ApiException(LocalDateTime.now(), exception.getMessage(), request.getDescription(false));
        return new ResponseEntity<>(apiException, HttpStatus.NOT_FOUND);
    }


    @ExceptionHandler(value = {RentalAlreadyExistsException.class})
    ResponseEntity<ApiException> handleRentalAlreadyExists(RentalAlreadyExistsException exception,
                                                           WebRequest request) {
        ApiException apiException = new ApiException(LocalDateTime.now(), exception.getMessage(), request.getDescription(false));
                return new ResponseEntity<>(apiException, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(value = {TooManyRentalsException.class})
    ResponseEntity<ApiException> handleTooManyRentals(TooManyRentalsException exception, WebRequest request) {
        ApiException apiException = new ApiException(LocalDateTime.now(), exception.getMessage(), request.getDescription(false));
        return new ResponseEntity<>(apiException, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(value = {SavedNewRentalException.class})
    ResponseEntity<ApiException> handleTooManyRentals(SavedNewRentalException exception, WebRequest request) {
        ApiException apiException = new ApiException(LocalDateTime.now(), exception.getMessage(), request.getDescription(false));
        return new ResponseEntity<>(apiException, HttpStatus.CREATED);
    }

    @ExceptionHandler(value = {BookAlreadyRentedByPersonException.class})
    ResponseEntity<ApiException> handleBookAlreadyRentedByPerson(BookAlreadyRentedByPersonException exception, WebRequest request) {
        ApiException apiException = new ApiException(LocalDateTime.now(), exception.getMessage(), request.getDescription(false));
        return new ResponseEntity<>(apiException, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(value = {BookTooLongNotReturnException.class})
    ResponseEntity<ApiException> handleBookTooLongNotReturnException(BookTooLongNotReturnException exception,
                                                                     WebRequest request) {
        ApiException apiException = new ApiException(LocalDateTime.now(), exception.getMessage(), request.getDescription(false));
        return new ResponseEntity<>(apiException, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
