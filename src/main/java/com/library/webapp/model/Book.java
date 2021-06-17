package com.library.webapp.model;

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
    private String bookcover;

    @OneToMany(mappedBy = "book", cascade = CascadeType.ALL)
    private List<Rental> rentalSet = new ArrayList<>();

    public Book(Long id, String title, String description, String isbn, String bookCover) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.isbn = isbn;
        this.bookcover = bookCover;
    }

    public Book(String title, String description, String isbn, String bookCover) {
        this.title = title;
        this.description = description;
        this.isbn = isbn;
        this.bookcover = bookCover;
    }

    public Book() {
    }

    public String getBookcover() {
        return bookcover;
    }

    public void setBookcover(String bookcover) {
        this.bookcover = bookcover;
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
