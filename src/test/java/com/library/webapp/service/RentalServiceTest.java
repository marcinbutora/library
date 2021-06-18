package com.library.webapp.service;

import com.library.webapp.exception.TooManyRentalsException;
import com.library.webapp.model.Book;
import com.library.webapp.model.Person;
import com.library.webapp.model.Rental;
import com.library.webapp.repository.RentalRepository;
import org.junit.jupiter.api.Assertions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.List;

@SpringBootTest
@Transactional
class RentalServiceTest {

    @Autowired
    private RentalRepository rentalRepository;

    @Autowired
    private RentalService rentalService;

    public void shouldThrowExceptionTooManyRentals() {
        // given
        Book book1 = new Book("test 1", "test desc 1", "1234", "test 1 book cover url");
        Book book2 = new Book("test 2", "test desc 2", "1234", "test 2 book cover url");
        Book book3 = new Book("test 3", "test desc 3", "1234", "test 3 book cover url");
        Book book4 = new Book("test 4", "test desc 4", "1234", "test 4 book cover url");
        Person person = new Person("Marcin", "Butora", "Żywiec", LocalDateTime.now());

        // when
        Rental rental1 = new Rental(book1, person, LocalDateTime.now());
        Rental rental2 = new Rental(book2, person, LocalDateTime.now());
        Rental rental3 = new Rental(book3, person, LocalDateTime.now());
        Rental rental4 = new Rental(book4, person, LocalDateTime.now());
        Rental rental5 = new Rental(book1, person, LocalDateTime.now());

        // then

    }
}