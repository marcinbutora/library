package com.library.webapp.book;


import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class BookService {

    private final BookRepository bookRepository;

    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public List<Book> findAllBooks() {
        return findBookByTitle("Getting all books", bookRepository.findAll(), "Books not found!");
    }

    public List<Book> findByTitle(String title) throws BookNotFoundException {
        return findBookByTitle("Getting info about all books by title",
                bookRepository.findByTitle(title),
                "Book with this title: (" + title + ") not found");
    }


    public BookDTO findById(Long id) throws BookNotFoundException {
        log.info("Finding book by id");
        return findBookById(id);
    }


    public Book save(Book book) {
        log.info("Saving new Book");
        return bookRepository.save(book);
    }

    public void delete(Book book) {
        log.info("Deleting book");
        bookRepository.delete(book);
    }

    private List<Book> findBookByTitle(String s, List<Book> bookRepository, String title) {
        log.info(s);
        if (bookRepository.isEmpty()) {
            throw new BookNotFoundException(title);
        }
        return bookRepository;
    }

    private BookDTO findBookById(Long id) {
        Optional<Book> foundedBook = bookRepository.findById(id);
        if (foundedBook.isEmpty()) {
            throw new BookNotFoundException("Book does not exist with this id: " + id);
        }
        return BookConverter.entityToDTO(foundedBook.get());
    }
}
