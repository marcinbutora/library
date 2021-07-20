package com.library.webapp.rental;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class TooManyRentalsException extends RuntimeException {
    public TooManyRentalsException() {
        super();
    }

    public TooManyRentalsException(String message) {
        super(message);
    }

    public TooManyRentalsException(String message, Throwable cause) {
        super(message, cause);
    }
}
