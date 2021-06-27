package com.library.webapp.rental.model;

import com.library.webapp.book.model.Book;
import com.library.webapp.person.model.Person;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public LocalDateTime getRentedDate() {
        return rentedDate;
    }

    public void setRentedDate(LocalDateTime rentedDate) {
        this.rentedDate = rentedDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Rental rental = (Rental) o;
        return id == rental.id && Objects.equals(book, rental.book) && Objects.equals(person, rental.person) && Objects.equals(rentedDate, rental.rentedDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, book, person, rentedDate);
    }
}
