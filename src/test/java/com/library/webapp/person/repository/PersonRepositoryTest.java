package com.library.webapp.person.repository;

import com.library.webapp.person.Person;
import com.library.webapp.person.PersonRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Transactional
class PersonRepositoryTest {
    @Autowired
    private PersonRepository personRepository;

    @Test
     void shouldCheckPersonsAreSaved() {
        // given
        Person marcin = new Person(1L, "Marcin", "Butora", "Żywiec", LocalDateTime.now());
        Person kacper = new Person(2L, "Kacper", "Butora", "Żywiec", LocalDateTime.now());
        Person maciej = new Person(3L, "Maciej", "Butora", "Żywiec", LocalDateTime.now());

        // when
        List<Person> savedPersons = personRepository.saveAll(List.of(marcin, kacper, maciej));

        // then
        assertThat(savedPersons).isNotEmpty();
    }

    @Test
    void shouldFindPersonById() {
        // given
        Person person = new Person("Marcin", "Butora", "Żywiec", LocalDateTime.now());
        person.setId(1L);
        personRepository.save(person);

        // when
        Optional<Person> personById = personRepository.findById(person.getId());

        //then
        assertThat(personById).isPresent();
    }

    @Test
    void shouldDoNotFindByPersonId() {
        // given
        Long randomPersonId = 4L;

        // when
        Optional<Person> personById = personRepository.findById(randomPersonId);

        // then
        assertThat(personById).isNotPresent();
    }

    @Test
    void shouldDeletePersonByPersonId() {
        // given
        Person person = new Person("Marcin", "Butora", "Żywiec", LocalDateTime.now());
        person.setId(1L);
        personRepository.save(person);

        // when
        personRepository.deletePersonByPersonId(person.getId());

        //then
        List<Person> personList = personRepository.findAll();
        assertThat(personList).isEmpty();
    }
}