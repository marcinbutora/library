package com.library.webapp.book;

public class BookConverter {
    public static BookDTO entityToDTO(Book book) {
        BookDTO bookDTO = new BookDTO();
        bookDTO.setTitle(book.title());
        bookDTO.setDescription(book.description());
        bookDTO.setIsbn(book.isbn());
        bookDTO.setBookcover(book.bookCover());
        return bookDTO;
    }
}
