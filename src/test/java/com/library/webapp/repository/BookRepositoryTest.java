package com.library.webapp.repository;

import com.library.webapp.model.Book;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.transaction.Transactional;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Transactional
class BookRepositoryTest {

    @Autowired
    private BookRepository bookRepository;

    @Test
    public void shouldReturnListOfSavedBooks() {
        // given
        Book bookOne = new Book(1L, "test", "test", "test");
        Book bookTwo = new Book(2L, "test", "test", "test");

        // when
        List<Book> savedBookToDatabase = bookRepository.saveAll(List.of(bookOne, bookTwo));

        // then
        assertThat(savedBookToDatabase).isEqualTo(List.of(bookOne, bookTwo));
    }

}