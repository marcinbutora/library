package com.library.webapp.controller;

import com.library.webapp.model.Book;
import com.library.webapp.service.BookService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@AllArgsConstructor
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/api")
public class BookController {

    private BookService bookService;

    @GetMapping(value = "/listBook")
    public List<Book> getAllBooks() {
        return bookService.findAllBooks();
    }

    @GetMapping(value = "/bookByTitle/{title}")
    public List<Book> getBookByName(@PathVariable("title") String title) {
        return bookService.findByTitle(title);
    }

    @GetMapping(value = "/bookById/{id}")
    public Optional<Book> getBookById(@PathVariable("id") Long id) {
        return bookService.findById(id);
    }

    @PostMapping(value = "/book/add")
    public ResponseEntity<?> saveBook(@RequestBody Book book) {
        bookService.save(book);
        return new ResponseEntity("Book saved successfully", HttpStatus.OK);
    }

    @PutMapping(value = "/book/update/{id}")
    public ResponseEntity<?> updateBook(@RequestBody Book book, @PathVariable("id") Long id) {
        if(bookService.findById(id).isPresent()) {
            bookService.save(book);
        } else {
            return new ResponseEntity("Book not found", HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity("Book updated successfully", HttpStatus.OK);
    }
}
