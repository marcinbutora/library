package com.library.webapp.person;

import com.library.webapp.person.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Repository
public interface PersonRepository extends JpaRepository<Person, Long> {
    List<Person> findByLastname(String lastname);

    void deletePersonByPersonId(Long id);
}
