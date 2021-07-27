package com.library.webapp.rental;

public class RentalAlreadyExistsException extends RuntimeException{
    public RentalAlreadyExistsException(String message) {
        super(message);
    }
}
