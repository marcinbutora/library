package com.library.webapp.controller;

import com.library.webapp.exception.ResourceNotFoundException;
import com.library.webapp.model.Person;
import com.library.webapp.service.PersonService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@AllArgsConstructor
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/api")
public class PersonController {
    private PersonService personService;


    @GetMapping(value = "/person/list")
    public List<Person> getAllPersons() {
        return personService.findAllPersons();
    }

    @GetMapping(value = "/person/list/bylastname/{lastname}")
    public List<Person> getPersonByLastname(@PathVariable("lastname") String lastname) {
        return personService.findByLastname(lastname);
    }

    @GetMapping(value = "/person/byid/{id}")
    public Optional<Person> getPersonById(@PathVariable("id") Long id) {
        return personService.findById(id);
    }

    @PostMapping(value = "/person/add")
    public ResponseEntity<?> savePerson(@RequestBody Person person) {
        personService.save(person);
        return new ResponseEntity("Person saved successfully", HttpStatus.OK);
    }

    @PutMapping(value = "/person/update/{id}")
    public Person updatePerson(@RequestBody Person person, @PathVariable("id") Long id) {
        return personService.findById(id).map(p -> {
            p.setFirstname(person.getFirstname());
            p.setLastname(person.getLastname());
            p.setCity(person.getCity());
            return personService.save(p);
        }).orElseThrow(() -> new ResourceNotFoundException("Person not found"));
    }
    @DeleteMapping(value = "/person/delete/{id}")
    public ResponseEntity<?> deletePerson(@PathVariable("id") Long id) {
        return personService.findById(id).map(p -> {
            personService.delete(p);
            return ResponseEntity.ok().build();
        }).orElseThrow(() -> new ResourceNotFoundException("Person not found"));
    }



}
