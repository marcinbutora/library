package com.library.webapp.person;

import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class PersonConverter {

    public PersonDTO entityToDto(Person person) {
        PersonDTO dto = new PersonDTO();
        dto.setId(person.getId());
        dto.setFirstname(person.getFirstname());
        dto.setLastname(person.getLastname());
        dto.setCity(person.getCity());
        return dto;
    }

    public List<PersonDTO> entityToDto(List<Person> personList) {
        return personList.stream().map(this::entityToDto).collect(Collectors.toList());
    }

    public Person dtoToEntity(PersonDTO personDTO) {
        Person person = new Person();
        person.setId(personDTO.getId());
        person.setFirstname(personDTO.getFirstname());
        person.setLastname(personDTO.getLastname());
        person.setCity(personDTO.getCity());
        return person;
    }

    public List<Person> dtoToEntity(List<PersonDTO> personDTOS) {
        return personDTOS.stream().map(this::dtoToEntity).collect(Collectors.toList());
    }
}
