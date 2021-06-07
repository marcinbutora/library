package com.library.webapp.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@Table(name = "rental")
public class Rental {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @ManyToOne
    @JoinColumn(name = "book_id")
    private Book book;
    @ManyToOne
    @JoinColumn(name = "person_id")
    private Person person;
    @Column(name = "rented_date")
    private LocalDateTime rentedDate;

    public Rental(Book book, Person person, LocalDateTime rentedDate) {
        this.book = book;
        this.person = person;
        this.rentedDate = rentedDate;
    }

    public Rental() {
    }
}
