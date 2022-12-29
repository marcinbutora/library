package com.library.webapp.person;

import com.library.webapp.rental.RentalAlreadyExistsException;
import com.library.webapp.exception.ResourceNotFoundException;
import com.library.webapp.rental.RentalService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/api/person")
public class PersonController {
    private final PersonService personService;
    private final RentalService rentalService;

    public PersonController(PersonService personService, RentalService rentalService) {
        this.personService = personService;
        this.rentalService = rentalService;
    }

    @GetMapping
    public List<Person> getAllPersons() {
        return personService.findAllPersons();
    }

    @GetMapping(value = "/bylastname/{lastname}")
    public List<Person> getPersonByLastname(@PathVariable String lastname) {
        return personService.findByLastname(lastname);
    }

    @GetMapping(value = "/{id}")
    public Person getPersonById(@PathVariable Long id) {
        return personService.findById(id).orElseThrow(() -> new PersonNotFoundException("Reader not found with id: " + id));
    }

    @PostMapping
    public ResponseEntity<?> savePerson(@RequestBody Person person) {
        personService.save(person);
        return new ResponseEntity<>("Person saved successfully", HttpStatus.OK);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<?> deletePerson(@PathVariable Long id) {
        Long countRentalsByPersonId = rentalService.countRentalsOfPerson(id);
        if (countRentalsByPersonId > 0) {
            throw new RentalAlreadyExistsException("Cannot be deleted because reader has " + countRentalsByPersonId + " unreturned books!");
        }
        return personService.findById(id).map(p -> {
            personService.delete(p);
            return ResponseEntity.ok().build();
        }).orElseThrow(() -> new ResourceNotFoundException("Person not found!"));
    }
}
