package com.library.webapp.person.repository;

import com.library.webapp.person.Person;
import com.library.webapp.person.PersonRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Transactional
class PersonRepositoryTest {
    @Autowired
    private PersonRepository personRepository;

    @Test
    public void shouldCheckPersonsAreSaved() {
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
    public void shouldFindPersonGivenByLastname() {
        // given
        Person person = new Person(1L, "Marcin", "Butora", "Żywiec", LocalDateTime.now());
        Person person2 = new Person(2L, "Marcin", "Kamiński", "Żywiec", LocalDateTime.now());

        // when
        List<Person> savedPersons = personRepository.saveAll(List.of(person, person2));

        // then
        assertThat(savedPersons.contains(personRepository.findByLastname("Butora")));
    }
}