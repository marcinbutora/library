package com.library.webapp.repository;

import com.library.webapp.person.model.Person;
import com.library.webapp.person.repository.PersonRepository;
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
    public void shouldReturnListOfSavedPersonsToDB() {
        // given
        Person personToSaveOne = new Person("Janina", "Kowalska", "Bielsko-Biała", LocalDateTime.of(2021, 10, 21, 22, 22, 00));
        Person personToSaveTwo = new Person("Jan", "Kowalski", "Bielsko-Biała", LocalDateTime.of(2021, 10, 22, 12, 44, 10));

        // when
        List<Person> savedPersonsToDB = personRepository.saveAll(List.of(personToSaveOne, personToSaveTwo));

        // then
        assertThat(savedPersonsToDB).isEqualTo(List.of(personToSaveOne, personToSaveTwo));
    }
}