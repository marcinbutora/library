package com.library.webapp.model;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Data
@Entity
@Getter
@Setter
@Table(name = "person")
public class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String firstname;
    private String lastname;
    private String city;

    public Person(Long id, String firstname, String lastname, String city) {
        this.id = id;
        this.firstname = firstname;
        this.lastname = lastname;
        this.city = city;
    }

    public Person(String firstname, String lastname, String city) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.city = city;
    }

    public Person() {
    }
}
