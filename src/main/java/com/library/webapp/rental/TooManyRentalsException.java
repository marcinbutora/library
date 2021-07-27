package com.library.webapp.rental;

public class TooManyRentalsException extends RuntimeException {
    public TooManyRentalsException(String message) {
        super(message);
    }
}
