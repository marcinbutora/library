package com.library.webapp.repository;

import com.library.webapp.model.Rental;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface RentalRepository extends JpaRepository<Rental, Long> {
}
