package com.library.webapp.rental;

public class BookTooLongNotReturnException extends RuntimeException {
    public BookTooLongNotReturnException(String message) {
        super(message);
    }
}
