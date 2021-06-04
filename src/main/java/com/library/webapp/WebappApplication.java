package com.library.webapp;

import com.library.webapp.model.Book;
import com.library.webapp.model.Person;
import com.library.webapp.repository.BookRepository;
import com.library.webapp.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

import java.util.List;

@SpringBootApplication
public class WebappApplication implements CommandLineRunner {

	@Autowired
	private PersonRepository personRepository;

	@Autowired
	private BookRepository bookRepository;

	public static void main(String[] args) {
		SpringApplication.run(WebappApplication.class, args);
	}

	public void run(String... args) throws Exception {
		Book book1 = new Book("Duchy", "Książka o duchach", "1234-5678");
		Book book2 = new Book("Kosmici", "Książka o kosmitach", "2345-6789");

		Person person1 = new Person("Stanisław", "Kowalski", "Międzyrzecze Górne");
		Person person2 = new Person("Aleksandra", "Nowicka", "Zalesie");

		bookRepository.saveAll(List.of(book1,book2));
		personRepository.saveAll(List.of(person1,person2));
	}


}
