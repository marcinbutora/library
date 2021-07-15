package com.library.webapp.rental.controller;

import com.library.webapp.rental.model.Rental;
import com.library.webapp.rental.service.RentalService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.TooManyListenersException;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/api/rentals")
public class RentalController {

    private RentalService rentalService;

    public RentalController(RentalService rentalService) {
        this.rentalService = rentalService;
    }

    @GetMapping("/list")
    public List<Rental> getAllRentals() {
        return rentalService.findAllRentals();
    }

    @GetMapping("/reader/{id}")
    public List<Rental> getAllRentalsForPersonId(@PathVariable Long id) {
        return rentalService.findAllByPersonId(id);
    }

    @GetMapping("/book/{id}")
    public List<Rental> getAllRentalsByBookId(@PathVariable Long id) {
        return rentalService.findAllByBookId(id);
    }

    @GetMapping("/count/reader/{id}")
    public Long count(@PathVariable Long id) {
        return rentalService.countRentalsOfPerson(id);
    }

    @PostMapping(value = "/{personId}/{bookId}")
    public ResponseEntity<?> saveRental(@PathVariable Long bookId, @PathVariable Long personId) throws TooManyListenersException {
        rentalService.saveNewRental(bookId, personId, LocalDateTime.now());
        return new ResponseEntity("Saved new rental", HttpStatus.OK);
    }
}
