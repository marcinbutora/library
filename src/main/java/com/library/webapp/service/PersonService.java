package com.library.webapp.service;

import com.library.webapp.model.Person;
import com.library.webapp.repository.PersonRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class PersonService {
    private PersonRepository personRepository;

    public PersonService(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    public List<Person> findAllPersons() {
        log.info("Getting info about persons");
        return personRepository.findAll();
    }

    public Optional<Person> findById(Long id) {
        log.info("Getting info about persons by id");
        return personRepository.findById(id);
    }

    public Person save(Person person) {
        log.info("Saving new person");
        return personRepository.save(person);
    }

    public List<Person> findByLastname(String lastname) {
        return personRepository.findByLastname(lastname);
    }

    public void delete(Person person) {
        log.info("Deleting person");
        personRepository.delete(person);
    }
}
