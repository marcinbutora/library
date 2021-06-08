package com.library.webapp.controller;

import com.library.webapp.model.Person;
import com.library.webapp.model.Rental;
import com.library.webapp.service.RentalService;
import lombok.AllArgsConstructor;
import org.springframework.data.jpa.repository.Query;
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

}
