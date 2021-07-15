package com.library.webapp.person.controller;

import com.library.webapp.exception.PersonNotFoundException;
import com.library.webapp.exception.ResourceNotFoundException;
import com.library.webapp.person.converter.PersonConverter;
import com.library.webapp.person.dto.PersonDTO;
import com.library.webapp.person.model.Person;
import com.library.webapp.person.service.PersonService;
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
    private PersonConverter personConverter;

    public PersonController(PersonService personService, PersonConverter personConverter) {
        this.personService = personService;
        this.personConverter = personConverter;
    }

    @GetMapping()
    public List<PersonDTO> getAllPersons() {
        List<Person> findAll = personService.findAllPersons();
        return personConverter.entityToDto(findAll);
    }

    @GetMapping(value = "/bylastname/{lastname}")
    public List<PersonDTO> getPersonByLastname(@PathVariable String lastname) {
        List<Person> getPersonsByLastname = personService.findByLastname(lastname);
        return personConverter.entityToDto(getPersonsByLastname);
    }

    @GetMapping(value = "/{id}")
    public Person getPersonById(@PathVariable Long id) {
        return personService.findById(id).orElseThrow(PersonNotFoundException::new);
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
