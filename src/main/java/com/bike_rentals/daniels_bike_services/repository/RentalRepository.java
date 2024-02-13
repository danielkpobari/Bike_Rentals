package com.bike_rentals.daniels_bike_services.repository;

import com.bike_rentals.daniels_bike_services.model.Rentals;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RentalRepository extends JpaRepository<Rentals, Long> {
}
