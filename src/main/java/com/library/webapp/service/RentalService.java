package com.library.webapp.service;

import com.library.webapp.model.Book;
import com.library.webapp.model.Person;
import com.library.webapp.model.Rental;
import com.library.webapp.repository.BookRepository;
import com.library.webapp.repository.PersonRepository;
import com.library.webapp.repository.RentalRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class RentalService {
    private RentalRepository rentalRepository;
    private PersonRepository personRepository;
    private BookRepository bookRepository;

    public RentalService(RentalRepository rentalRepository, PersonRepository personRepository, BookRepository bookRepository) {
        this.rentalRepository = rentalRepository;
        this.personRepository = personRepository;
        this.bookRepository = bookRepository;
    }

    public List<Rental> findAllRentals() {
        log.info("Finding all rentals");
        return rentalRepository.findAll();
    }

    public boolean saveNewRental(Long bookId, Long personId, LocalDateTime rentedDate) {
        Optional<Person> foundedPerson = personRepository.findById(personId);
        Optional<Book> foundedBook = bookRepository.findById(bookId);
        if(foundedPerson.isPresent() && foundedBook.isPresent()) {
            rentalRepository.save(new Rental(foundedBook.get(), foundedPerson.get(), rentedDate));
            return true;
        }
        return false;
    }

    public List<Rental> findAllByPersonId(Long id) {
        log.info("Finding all rentals by person id");
        return rentalRepository.findRentalByPersonId(id);
    }

    public List<Rental> findAllByBookId(Long id) {
        log.info("Finding all rentals by book id");
        return rentalRepository.findRentalByBookId(id);
    }

    public Long countRentalsOfPerson(Long id) {
        log.info("Counting records of person");
        return rentalRepository.countRentalByPersonId(id);
    }
}
