package com.library.webapp.controller;

import com.library.webapp.model.Book;
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

    @GetMapping(value = "/listPerson")
    public List<Person> getAllPersons() {
        return personService.findAllPersons();
    }

    @GetMapping(value = "/personByLastname/{lastname}")
    public List<Person> getPersonByLastname(@PathVariable("lastname") String lastname) {
        return personService.findByLastname(lastname);
    }

    @GetMapping(value = "/personById/{id}")
    public Optional<Person> getPersonById(@PathVariable("id") Long id) {
        return personService.findById(id);
    }

    @PostMapping(value = "/person/add")
    public ResponseEntity<?> savePerson(@RequestBody Person person) {
        personService.save(person);
        return new ResponseEntity("Person saved successfully", HttpStatus.OK);
    }

    @PutMapping(value = "/person/update/{id}")
    public ResponseEntity<?> updatePerson(@RequestBody Person person, @PathVariable("id") Long id) {
        if(personService.findById(id).isPresent()) {
            personService.save(person);
        } else {
            return new ResponseEntity("Person not found", HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity("Person updated successfully", HttpStatus.OK);
    }



}
