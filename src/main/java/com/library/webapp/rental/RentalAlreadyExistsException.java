package com.library.webapp.rental;

import org.springframework.http.HttpStatus;

public class RentalAlreadyExistsException extends RuntimeException{
    private HttpStatus status;
    private String message;

    public RentalAlreadyExistsException(String message, HttpStatus status) {
        super(message);
        this.status = status;
    }

    public RentalAlreadyExistsException(String message, Throwable cause) {
        super(message, cause);
    }
}
