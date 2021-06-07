package com.library.webapp.controller;

import com.library.webapp.model.Rental;
import com.library.webapp.service.RentalService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
@CrossOrigin
@RequestMapping("/api")
public class RentalController {

    private RentalService rentalService;

    @GetMapping("/rental/list")
    public List<Rental> getAllRentals() {
        return rentalService.findAllRentals();
    }
}
