package com.library.webapp.rental;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface RentalRepository extends JpaRepository<Rental, Long> {
    List<Rental> findRentalsByPersonId(Long id);

    List<Rental> findRentalsByBookId(Long id);

    @Query("select datediff(CURRENT_DATE, r.rentedDate) as dateDiff from Rental r where r.person.id=:personId")
    List<Long> countHowManyDaysBookIsRented(Long personId);

    @Query("select count(r) from Rental r where r.person.id = :personId and datediff(CURRENT_DATE, r" +
            ".rentedDate) > " +
            "30")
    Long countAllExpiredRentals(@Param("personId") Long personId);

    Long countRentalByPersonId(Long id);
}
