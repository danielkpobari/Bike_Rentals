package com.bike_rentals.daniels_bike_services.repository;

import com.bike_rentals.daniels_bike_services.model.Bike;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BikeRepository extends JpaRepository<Bike, Long> {
//    Iterable<Bike> findByUserId(Long userId);
    List<Bike> findByUserId(Long userId);
}
