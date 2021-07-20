package com.library.webapp.book;

public class BookDTO {
    private String title;
    private String description;
    private String isbn;
    private String bookcover;

    public BookDTO(String title, String description, String isbn, String bookcover) {
        this.title = title;
        this.description = description;
        this.isbn = isbn;
        this.bookcover = bookcover;
    }

    public BookDTO() {
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

    public String getBookcover() {
        return bookcover;
    }

    public void setBookcover(String bookcover) {
        this.bookcover = bookcover;
    }
}
