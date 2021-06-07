package com.library.webapp.model;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

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
}
