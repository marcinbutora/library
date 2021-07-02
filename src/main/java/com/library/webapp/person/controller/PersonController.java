package com.library.webapp.person.controller;

import com.library.webapp.exception.ResourceNotFoundException;
import com.library.webapp.person.model.Person;
import com.library.webapp.person.service.PersonService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/api/readers")
public class PersonController {
    private PersonService personService;

    public PersonController(PersonService personService) {
        this.personService = personService;
    }

    @GetMapping(value = "/list")
    public List<Person> getAllPersons() {
        return personService.findAllPersons();
    }

    @GetMapping(value = "/bylastname/{lastname}")
    public List<Person> getPersonByLastname(@PathVariable String lastname) {
        return personService.findByLastname(lastname);
    }

    @GetMapping(value = "/byid/{id}")
    public Optional<Person> getPersonById(@PathVariable Long id) {
        return personService.findById(id);
    }

    @PostMapping()
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
        return personService.findById(id).map(p -> {
            personService.delete(p);
            return ResponseEntity.ok().build();
        }).orElseThrow(() -> new ResourceNotFoundException("Person not found"));
    }



}
