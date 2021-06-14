package com.library.webapp;

import com.library.webapp.model.Book;
import com.library.webapp.model.Person;
import com.library.webapp.model.Rental;
import com.library.webapp.repository.BookRepository;
import com.library.webapp.repository.PersonRepository;
import com.library.webapp.repository.RentalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.time.LocalDateTime;
import java.util.List;

@SpringBootApplication
public class WebappApplication implements CommandLineRunner {

    @Autowired
    private PersonRepository personRepository;

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private RentalRepository rentalRepository;

    public static void main(String[] args) {
        SpringApplication.run(WebappApplication.class, args);
    }

    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                WebMvcConfigurer.super.addCorsMappings(registry);
                registry.addMapping("/**")
                        .allowedOrigins("http://localhost:4200")
                        .allowedMethods("PUT", "DELETE", "GET", "POST")
                        .allowedHeaders("*");
            }
        };
    }

    public void run(String... args) throws Exception {
        Book duchy = new Book("Duchy", "Książka o duchach", "1234-5678");
        Book kosmici = new Book("Kosmici", "Książka o kosmitach", "2345-6789");
        Book titanic = new Book("Titanic", "O statku", "125383838");
        bookRepository.saveAll(List.of(duchy, kosmici, titanic));

        Person person1 = new Person("Stanisław", "Kowalski", "Międzyrzecze Górne", LocalDateTime.of(2021, 10, 21, 10, 20, 00));
        Person person2 = new Person("Aleksandra", "Nowicka", "Zalesie", LocalDateTime.of(2021, 10, 22, 15, 22, 45));
        Person ania = new Person("Anna", "Butora", "Twardorzeczka", LocalDateTime.of(2021, 6, 9, 12, 15, 22));
        Person Zuzia = new Person("Zuzanna", "Butora", "Żywiec", LocalDateTime.of(2016, 1, 6, 21, 30, 00));
        personRepository.saveAll(List.of(person1, person2, ania, Zuzia));

        Rental rental1 = new Rental(duchy, person1, LocalDateTime.of(2021, 8, 16, 19, 22, 00));
        Rental rental2 = new Rental(kosmici, person2, LocalDateTime.of(2021, 8, 17, 20, 15, 00));
        Rental rental3 = new Rental(duchy, person2, LocalDateTime.now());
        Rental rentalania = new Rental(duchy, ania, LocalDateTime.now());
        Rental zuziakosmici = new Rental(kosmici, Zuzia, LocalDateTime.now());
        Rental zuziaduchy = new Rental(duchy, Zuzia, LocalDateTime.now());
        Rental stanislawtitanic = new Rental(titanic, person1, LocalDateTime.now());
        rentalRepository.saveAll(List.of(rental1, rental2, rental3, rentalania, zuziakosmici, zuziaduchy, stanislawtitanic));
    }


}
