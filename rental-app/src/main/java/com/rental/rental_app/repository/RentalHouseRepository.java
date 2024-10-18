package com.rental.rental_app.repository;

import com.rental.rental_app.entity.RentalHouse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RentalHouseRepository extends JpaRepository<RentalHouse, Long> {
}
