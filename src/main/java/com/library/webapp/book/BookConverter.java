package com.library.webapp.book;

public class BookConverter {
    public static BookDTO entityToDTO(Book book) {
        BookDTO bookDTO = new BookDTO();
        bookDTO.setTitle(book.getTitle());
        bookDTO.setDescription(book.getDescription());
        bookDTO.setIsbn(book.getIsbn());
        bookDTO.setBookcover(book.getBookcover());
        return bookDTO;
    }

    public static Book dtoToEntity(BookDTO bookDTO) {
        Book book = new Book();
        book.setTitle(bookDTO.getTitle());
        book.setDescription(bookDTO.getDescription());
        book.setIsbn(bookDTO.getIsbn());
        book.setBookcover(bookDTO.getBookcover());
        return book;
    }

}
