package com.library.webapp.service;

import com.library.webapp.model.Person;
import com.library.webapp.model.Rental;
import com.library.webapp.repository.RentalRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Collections;
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
}
