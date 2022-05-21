package com.library.webapp.book;


import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
public class BookService {

    private final BookRepository bookRepository;

    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public List<Book> findAllBooks() {
        log.info("Getting all books");
        List<Book> foundedBooks = bookRepository.findAll();
        if(foundedBooks.isEmpty()) {
            throw new BookNotFoundException("Books not found!");
        }
        return foundedBooks;
    }

    public List<Book> findByTitle(String title) throws BookNotFoundException {
        log.info("Getting info about all books by title");
        List<Book> foundedBooks = bookRepository.findByTitle(title);
        if (foundedBooks.isEmpty()) {
            throw new BookNotFoundException("Book with this title: (" +title+ ") not found");
        }
        return foundedBooks;
    }

    public BookDTO findById(Long id) throws BookNotFoundException {
        log.info("Finding book by id");
        Optional<Book> foundedBook = bookRepository.findById(id);
        if(foundedBook.isEmpty()) {
            throw new BookNotFoundException("Book does not exist with this id: "+id);
        }
        return BookConverter.entityToDTO(foundedBook.get());
    }

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
