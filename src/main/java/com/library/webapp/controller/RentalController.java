package com.library.webapp.controller;

import com.library.webapp.model.Person;
import com.library.webapp.model.Rental;
import com.library.webapp.service.RentalService;
import lombok.AllArgsConstructor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/api")
public class RentalController {

    private RentalService rentalService;

    public RentalController(RentalService rentalService) {
        this.rentalService = rentalService;
    }

    @GetMapping("/rental/list")
    public List<Rental> getAllRentals() {
        return rentalService.findAllRentals();
    }

    @GetMapping("/rentals/person/{id}")
    public List<Rental> getAllRentalsForPersonId(@PathVariable Long id) {
        return rentalService.findAllByPersonId(id);
    }

    @GetMapping("/rentals/book/{id}")
    public List<Rental> getAllRentalsByBookId(@PathVariable Long id) {
        return rentalService.findAllByBookId(id);
    }

    @GetMapping("/rentals/count/person/{id}")
    public Long count(@PathVariable Long id) {
        return rentalService.countRentalsOfPerson(id);
    }

    @PostMapping(value = "/rental/add")
    public ResponseEntity<?> saveRental(@RequestBody Rental rental) {
        rentalService.saveNewRental(rental);
        return new ResponseEntity("Saved new rental", HttpStatus.OK);
    }
}
