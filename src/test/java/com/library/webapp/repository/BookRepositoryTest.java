package com.library.webapp.repository;

import com.library.webapp.model.Book;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Transactional
class BookRepositoryTest {

    @Autowired
    private BookRepository bookRepository;

    @Test
    public void shouldReturnListOfSavedBooks() {
        // given
        Book bookOne = new Book(1L, "test", "test", "test", "test url");
        Book bookTwo = new Book(2L, "test", "test", "test", "test url");

        // when
        List<Book> savedBookToDatabase = bookRepository.saveAll(List.of(bookOne, bookTwo));

        // then
        assertThat(savedBookToDatabase).isEqualTo(List.of(bookOne, bookTwo));
    }

    @Test
    public void shouldFindBookByTitleLocal() {
        // given
        Book bookToFind = new Book("test", "test", "test", "test desc");

        // when
        List<Book> foundedBook = bookRepository.findByTitle("test");

        // then
        assertThat(foundedBook).isEqualTo(bookToFind);
    }

    @Test
    public void shouldFindBookByTitleDb() {
        // given
        Book bookToFind = new Book("test", "test", "test", "test");

        // when
        Book savedBook = bookRepository.save(bookToFind);
        List<Book> foundedBook = bookRepository.findByTitle("test");

        // then
        assertThat(savedBook).isEqualTo(foundedBook);
    }

}