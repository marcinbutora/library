package com.library.webapp.repository;

import com.library.webapp.model.Rental;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;


@Repository
public interface RentalRepository extends JpaRepository<Rental, Long> {
    List<Rental> findRentalByPersonId(Long id);
    List<Rental> findRentalByBookId(Long id);
    Long countRentalByPersonId(Long id);
}
