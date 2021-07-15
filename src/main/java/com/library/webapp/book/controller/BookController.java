package com.library.webapp.book.controller;

import com.library.webapp.exception.ResourceNotFoundException;
import com.library.webapp.book.model.Book;
import com.library.webapp.book.service.BookService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/api/book")
public class BookController {

    private BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping()
    public List<Book> getAllBooks() {
        return bookService.findAllBooks();
    }

    @GetMapping(value = "/bytitle/{title}")
    public List<Book> getBookByName(@PathVariable("title") String title) {
        return bookService.findByTitle(title);
    }

    @GetMapping(value = "/{id}")
    public Optional<Book> getBookById(@PathVariable("id") Long id) {
        return bookService.findById(id);
    }

    @PostMapping()
    public ResponseEntity<?> saveBook(@RequestBody Book book) {
        bookService.save(book);
        return new ResponseEntity("Book saved successfully", HttpStatus.OK);
    }

    @PutMapping(value = "/{id}")
    public Book updateBook(@RequestBody Book book, @PathVariable("id") Long id) {
        return bookService.findById(id).map(b -> {
            b.setTitle(book.getTitle());
            b.setDescription(book.getDescription());
            b.setIsbn(book.getIsbn());
            return bookService.save(b);
        }).orElseThrow(() -> new ResourceNotFoundException("Book not found"));
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<?> deleteBook(@PathVariable("id") Long id) {
        return bookService.findById(id).map(b -> {
            bookService.delete(b);
            return ResponseEntity.ok().build();
        }).orElseThrow(() -> new ResourceNotFoundException("Book not found"));
    }
}
