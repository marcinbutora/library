package com.library.webapp.person;

import com.library.webapp.rental.RentalAlreadyExistsException;
import com.library.webapp.exception.ResourceNotFoundException;
import com.library.webapp.rental.RentalService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/api/person")
public class PersonController {
    private PersonService personService;
    private RentalService rentalService;

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
    public Optional<Person> getPersonById(@PathVariable Long id) {
        return personService.findById(id);
    }

    @PostMapping
    public ResponseEntity<?> savePerson(@RequestBody Person person) {
        personService.save(person);
        return new ResponseEntity("Person saved successfully", HttpStatus.OK);
    }

    @PutMapping(value = "/{id}")
    public Person updatePerson(@RequestBody Person person, @PathVariable Long id) {
        return personService.findById(id).map(p -> {
            p.setFirstname(person.getFirstname());
            p.setLastname(person.getLastname());
            p.setCity(person.getCity());
            return personService.save(p);
        }).orElseThrow(() -> new ResourceNotFoundException("Person not found"));
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<?> deletePerson(@PathVariable Long id) {
        if (rentalService.countRentalsOfPerson(id) > 0) {
            throw new RentalAlreadyExistsException("Cannot be deleted because reader has unreturned books!", HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return personService.findById(id).map(p -> {
            personService.delete(p);
            return ResponseEntity.ok().build();
        }).orElseThrow(() -> new ResourceNotFoundException("Person not found"));
    }
}
