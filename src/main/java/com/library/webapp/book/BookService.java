package com.library.webapp.book;


import com.library.webapp.book.BookConverter;
import com.library.webapp.book.BookDTO;
import com.library.webapp.book.Book;
import com.library.webapp.book.BookRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class BookService {

    private BookRepository bookRepository;

    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public List<Book> findAllBooks() {
        log.info("Getting info about all books");
        return bookRepository.findAll();
    }

    public List<Book> findByTitle(String title) {
        log.info("Getting info about all books by title");
        return bookRepository.findByTitle(title);
    }

//    public Optional<BookDTO> findById(Long id) {
//        log.info("Getting info about all books by title");
//        return bookRepository.findById(id);
//    }

//    public Book save(Book book) {
//        log.info("Saving new book");
//        return bookRepository.save(book);
//    }

    public BookDTO save(BookDTO bookDTO) {
        log.info("Saving new Book");
        Book book = bookRepository.save(BookConverter.dtoToEntity(bookDTO));
        return BookConverter.entityToDTO(book);
    }

    public void delete(Book book) {
        log.info("Deleting book");
        bookRepository.delete(book);
    }
}
