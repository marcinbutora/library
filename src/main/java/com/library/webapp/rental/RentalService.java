package com.library.webapp.rental;

import com.library.webapp.book.Book;
import com.library.webapp.book.BookNotFoundException;
import com.library.webapp.book.BookRepository;
import com.library.webapp.person.Person;
import com.library.webapp.person.PersonNotFoundException;
import com.library.webapp.person.PersonRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

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

    public Rental saveNewRental(Long bookId, Long personId, LocalDateTime rentedDate) {
        log.info("Saving new rental");

        // check if the person exists
        Optional<Person> foundedPerson = personRepository.findById(personId);
        if (foundedPerson.isEmpty()) {
            throw new PersonNotFoundException(String.format("Person with this id (%d) not found!", personId));
        }

        // check if the book exists
        Optional<Book> foundedBook = bookRepository.findById(bookId);
        if (foundedBook.isEmpty()) {
            throw new BookNotFoundException(String.format("Book with this id not found: %d", bookId));
        }

        // check if the person has already borrowed this book
        List<Rental> getAllRentals = rentalRepository.findAll();
        List<Rental> foundedRentalByPersonAndBookId = getAllRentals.stream()
                .filter(a -> a.getPerson().getId().equals(foundedPerson.get().getId()) && a.getBook().id().equals(foundedBook.get().id()))
                .toList();
        if (!foundedRentalByPersonAndBookId.isEmpty()) {
            throw new BookAlreadyRentedByPersonException(String.format("Book was already rented by %s %s",
                    foundedPerson.get().getFirstname(), foundedPerson.get().getLastname()));
        }

        // check if the person is in arrears with returning a book for more than 30 days
        List<Long> allExpiredRentals = rentalRepository.countHowManyDaysBookIsRented(personId);
        Long countHowManyBooksAreNotReturned = rentalRepository.countAllExpiredRentals(personId);

        for (Long allExpiredRental : allExpiredRentals) {
            if (allExpiredRental > 30) {
                throw new BookTooLongNotReturnException(String.format("Reader has %d not returned book over 30 days!",
                        countHowManyBooksAreNotReturned));
            }
        }

        // check if the person has already borrowed 4 books - if so, do not borrow another one
        if (rentalRepository.countRentalByPersonId(personId) == 4) {
            throw new TooManyRentalsException("Reader have maximum number of rentals!");
        }

        // final save, if everything above is ok
        return rentalRepository.save(new Rental(foundedBook.get(), foundedPerson.get(), rentedDate));
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
