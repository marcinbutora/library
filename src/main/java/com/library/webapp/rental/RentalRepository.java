package com.library.webapp.rental;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface RentalRepository extends JpaRepository<Rental, Long> {
    List<Rental> findRentalsByPersonId(Long id);

    List<Rental> findRentalsByBookId(Long id);

    Long countRentalByPersonId(Long id);
}
