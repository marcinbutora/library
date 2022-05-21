package com.library.webapp.book;

import com.library.webapp.rental.Rental;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "book")
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String description;
    private String isbn;
    private String bookCover;

    @OneToMany(mappedBy = "book", cascade = CascadeType.ALL)
    private final List<Rental> rentalSet = new ArrayList<>();

    public Book(Long id, String title, String description, String isbn, String bookCover) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.isbn = isbn;
        this.bookCover = bookCover;
    }

    public Book(String title, String description, String isbn, String bookCover) {
        this.title = title;
        this.description = description;
        this.isbn = isbn;
        this.bookCover = bookCover;
    }

    public Book() {
    }

    public String getBookCover() {
        return bookCover;
    }

    public void setBookCover(String bookCover) {
        this.bookCover = bookCover;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }
}
