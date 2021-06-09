package com.library.webapp.service;

import com.library.webapp.model.Rental;
import com.library.webapp.repository.RentalRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class RentalService {
    private RentalRepository rentalRepository;

    public RentalService(RentalRepository rentalRepository) {
        this.rentalRepository = rentalRepository;
    }

    public void addNewRental(Rental rental) {
        rentalRepository.save(rental);
    }

    public List<Rental> findAllRentals() {
        log.info("Finding all rentals");
        return rentalRepository.findAll();
    }

    public Rental saveNewRental(Rental rental) {
        log.info("Saving new rental");
        return rentalRepository.save(rental);
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
