package com.library.webapp.rental;

import com.library.webapp.book.Book;
import com.library.webapp.person.Person;

import javax.persistence.*;
import java.time.LocalDateTime;

public record Rental(
        @ManyToOne
        @JoinColumn(name = "book_id")
        Book book,
        @ManyToOne
        @JoinColumn(name = "person_id")
        Person person,
        @Column(name = "rented_date")
        LocalDateTime rentedDate
) {}
