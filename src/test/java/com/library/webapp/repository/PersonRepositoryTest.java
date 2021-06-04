package com.library.webapp.repository;

import com.library.webapp.model.Person;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.transaction.Transactional;
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
        Person personToSaveOne = new Person("Janina", "Kowalska", "Bielsko-Biała");
        Person personToSaveTwo = new Person("Jan", "Kowalski", "Bielsko-Biała");

        // when
        List<Person> savedPersonsToDB = personRepository.saveAll(List.of(personToSaveOne, personToSaveTwo));

        // then
        assertThat(savedPersonsToDB).isEqualTo(List.of(personToSaveOne,personToSaveTwo));
    }
}