package com.bike_rentals.daniels_bike_services.service;

import com.bike_rentals.daniels_bike_services.model.Bike;
import com.bike_rentals.daniels_bike_services.model.Rentals;

public interface BikeService {
    Bike addBike(Bike bike);
    Iterable<Bike> getAllBikes();
    Iterable<Bike> getUserSelection(Long userId);
    Rentals rentBike(Long userId, Long bikeId);
    Rentals returnBike(Long rentalId);
}
