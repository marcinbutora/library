package com.library.webapp.repository;

import com.library.webapp.model.Rental;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.w3c.dom.stylesheets.LinkStyle;

import java.util.List;


@Repository
public interface RentalRepository extends JpaRepository<Rental, Long> {
    List<Rental> findRentalByPersonId(Long id);
    List<Rental> findRentalByBookId(Long id);
}
