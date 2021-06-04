package com.library.webapp.controller;

import com.library.webapp.exception.ResourceNotFoundException;
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
    public Book updateBook(@RequestBody Book book, @PathVariable("id") Long id) {
        return bookService.findById(id).map(b -> {
            b.setTitle(book.getTitle());
            b.setDescription(book.getDescription());
            b.setIsbn(book.getIsbn());
            return bookService.save(b);
        }).orElseThrow(() -> new ResourceNotFoundException("Book " + book.getTitle() + "not found"));
    }

    @DeleteMapping(value = "/book/delete/{id}")
    public ResponseEntity<?> deleteBook(@PathVariable("id") Long id) {
        return bookService.findById(id).map(b -> {
            bookService.delete(b);
            return ResponseEntity.ok().build();
        }).orElseThrow(() -> new ResourceNotFoundException("Book not found"));
    }
}
