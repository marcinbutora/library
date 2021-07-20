package com.library.webapp.person;

public class PersonNotFoundException extends RuntimeException {
    public PersonNotFoundException() {
    }
    public PersonNotFoundException(String message) {
        super(message);
    }
}
