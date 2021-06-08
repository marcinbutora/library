package com.library.webapp.model;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "person")
public class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String firstname;
    private String lastname;
    private String city;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime created;

    @OneToMany(mappedBy = "person", cascade = CascadeType.ALL)
    private List<Rental> rentalSet = new ArrayList<>();

    public Person(Long id, String firstname, String lastname, String city, LocalDateTime created) {
        this.id = id;
        this.firstname = firstname;
        this.lastname = lastname;
        this.city = city;
        this.created = created;
    }

    public Person(String firstname, String lastname, String city, LocalDateTime created) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.city = city;
        this.created = created;
    }

    public Person() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public LocalDateTime getCreated() {
        return created;
    }

    public void setCreated(LocalDateTime created) {
        this.created = created;
    }
}
