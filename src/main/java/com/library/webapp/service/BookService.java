package com.library.webapp.service;


import com.library.webapp.model.Book;
import com.library.webapp.repository.BookRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
@Slf4j
public class BookService {

    private BookRepository bookRepository;

    public List<Book> findAllBooks() {
        log.info("Getting info about all books");
        return bookRepository.findAll();
    }

    public List<Book> findByTitle(String title) {
        log.info("Getting info about all books by title");
        return bookRepository.findByTitle(title);
    }

    public Optional<Book> findById(Long id) {
        log.info("Getting info about all books by title");
        return bookRepository.findById(id);
    }

    public Book save(Book book) {
        log.warn("Saving new book");
        return bookRepository.save(book);
    }

    public void delete(Book book) {
        log.info("Deleting book");
        bookRepository.delete(book);
    }
}
