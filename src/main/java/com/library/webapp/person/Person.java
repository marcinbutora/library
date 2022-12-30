package com.library.webapp.person;

import com.library.webapp.rental.Rental;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

public record Person(
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        Long id,
        String firstname,
        String lastname,
        String city,
        @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
        LocalDateTime created,
        @OneToMany(mappedBy = "person", cascade = CascadeType.ALL)
        List<Rental> rentalSet
) { }
