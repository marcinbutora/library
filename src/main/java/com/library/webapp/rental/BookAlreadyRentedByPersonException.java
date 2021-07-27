package com.library.webapp.rental;

public class BookAlreadyRentedByPersonException extends RuntimeException {
    public BookAlreadyRentedByPersonException(String message) {
        super(message);
    }
}
