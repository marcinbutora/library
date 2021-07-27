package com.library.webapp.rental;

import com.library.webapp.book.Book;
import com.library.webapp.book.BookNotFoundException;
import com.library.webapp.book.BookRepository;
import com.library.webapp.person.Person;
import com.library.webapp.person.PersonNotFoundException;
import com.library.webapp.person.PersonRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
public class RentalService {
    private final RentalRepository rentalRepository;
    private final PersonRepository personRepository;
    private final BookRepository bookRepository;

    public RentalService(RentalRepository rentalRepository, PersonRepository personRepository, BookRepository bookRepository) {
        this.rentalRepository = rentalRepository;
        this.personRepository = personRepository;
        this.bookRepository = bookRepository;
    }

    public List<Rental> findAllRentals() {
        log.info("Finding all rentals");
        return rentalRepository.findAll();
    }

    public ResponseEntity<Rental> saveNewRental(Long bookId, Long personId, LocalDateTime rentedDate) {
        log.info("Saving new rental");
        Optional<Person> foundedPerson = personRepository.findById(personId);
        if (foundedPerson.isEmpty()) {
            throw new PersonNotFoundException(String.format("Person with this id (%d) not found!", personId));
        }
        Optional<Book> foundedBook = bookRepository.findById(bookId);
        if (foundedBook.isEmpty()) {
            throw new BookNotFoundException(String.format("Book with this id not found: %d", bookId));
        }
        List<Rental> getAllRentals = rentalRepository.findAll();
        List<Rental> filteredRentals = getAllRentals.stream()
                .filter(a -> a.getPerson().getId().equals(foundedPerson.get().getId()) && a.getBook().getId().equals(foundedBook.get().getId()))
                .collect(Collectors.toList());
        if (!filteredRentals.isEmpty()) {
            throw new BookAlreadyRentedByPersonException(String.format("Book was already rented by %s %s",
                    foundedPerson.get().getFirstname(), foundedPerson.get().getLastname()));
        }
        if (rentalRepository.countRentalByPersonId(personId) == 4) {
            throw new TooManyRentalsException("Reader have maximum number of rentals!");
        } else {
            rentalRepository.save(new Rental(foundedBook.get(), foundedPerson.get(), rentedDate));
            throw new SavedNewRentalException(String.format("Rental saved! %s %s rented a %s",
                    foundedPerson.get().getFirstname(), foundedPerson.get().getLastname(), foundedBook.get().getTitle()));
        }
    }

    public List<Rental> findAllByPersonId(Long id) {
        log.info("Finding all rentals by person id");
        return rentalRepository.findRentalsByPersonId(id);
    }

    public List<Rental> findAllByBookId(Long id) {
        log.info("Finding all rentals by book id");
        return rentalRepository.findRentalsByBookId(id);
    }

    public Long countRentalsOfPerson(Long id) {
        log.info("Counting records of person");
        return rentalRepository.countRentalByPersonId(id);
    }
}
