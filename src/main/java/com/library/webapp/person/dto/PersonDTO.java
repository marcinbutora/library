package com.library.webapp.person.dto;

import lombok.Data;

@Data
public class PersonDTO {
    private Long id;
    private String firstname;
    private String lastname;
    private String city;

    public PersonDTO(Long id, String firstname, String lastname, String city) {
        this.id = id;
        this.firstname = firstname;
        this.lastname = lastname;
        this.city = city;
    }

    public PersonDTO() {
    }
}
