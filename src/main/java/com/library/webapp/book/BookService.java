package com.library.webapp.book;


import com.library.webapp.book.BookConverter;
import com.library.webapp.book.BookDTO;
import com.library.webapp.book.Book;
import com.library.webapp.book.BookRepository;
import com.library.webapp.person.Person;
import com.library.webapp.person.PersonDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.awt.print.Pageable;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
public class BookService {

    private BookRepository bookRepository;

    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public List<BookDTO> findAllBooks() {
        log.info("Getting all books");
        return bookRepository.findAll()
                .stream()
                .map(BookConverter::entityToDTO)
                .collect(Collectors.toList());
    }

    public List<BookDTO> findByTitle(String title) {
        log.info("Getting info about all books by title");
        return bookRepository.findByTitle(title)
                .stream()
                .map(BookConverter::entityToDTO)
                .collect(Collectors.toList());
    }

    public BookDTO findById(Long id) {
        log.info("Getting info about all books by title");
        return (BookDTO) bookRepository.findById(id)
                .stream()
                .map(BookConverter::entityToDTO);
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
