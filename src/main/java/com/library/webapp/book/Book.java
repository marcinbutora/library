package com.library.webapp.book;

import com.library.webapp.rental.Rental;

import javax.persistence.*;
import java.util.List;
@Table(name = "book")
public record Book(
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        Long id,
        String title,
        String description,
        String isbn,
        String bookCover,
        @OneToMany(mappedBy = "book", cascade = CascadeType.ALL)
        List<Rental> rentalSet
){}
