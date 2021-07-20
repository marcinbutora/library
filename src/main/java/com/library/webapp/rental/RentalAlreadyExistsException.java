package com.library.webapp.rental;

import org.springframework.http.HttpStatus;

public class RentalAlreadyExistsException extends RuntimeException{
    public RentalAlreadyExistsException(String message) {
        super(message);
    }
}
