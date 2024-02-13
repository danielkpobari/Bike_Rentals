package com.bike_rentals.daniels_bike_services.service;

import com.bike_rentals.daniels_bike_services.exception.ResourceNotFoundException;
import com.bike_rentals.daniels_bike_services.model.Bike;
import com.bike_rentals.daniels_bike_services.model.Rentals;
import com.bike_rentals.daniels_bike_services.repository.BikeRepository;
import com.bike_rentals.daniels_bike_services.repository.RentalRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;


@Service
@Slf4j
public class BikeServiceImpl implements BikeService{
    @Autowired
    private BikeRepository bikeRepository;

    @Autowired
    private RentalRepository rentalRepository;

    public Bike addBike(Bike bike) {
        return bikeRepository.save(bike);
    }

    public  Iterable<Bike> getAllBikes() {
        return bikeRepository.findAll();
    }


    public Iterable<Bike> getUserSelection(Long userId) {
        return bikeRepository.findByUserId(userId);
    }

    @Transactional
    @Override
    public Rentals rentBike(Long userId, Long bikeId) {
        // Check if the bike is available for rent
        log.info("Attempting to rent bike with id {} for user with id {}", bikeId, userId);
        Bike bike = bikeRepository.findById(bikeId)
                .orElseThrow(() -> new ResourceNotFoundException("Bike", "id", bikeId));
        if (bike.isRented()) {
            throw new IllegalStateException("Bike is already rented");
        }

        // Update bike status to rented
        bike.setRented(true);
        bikeRepository.save(bike);

        // Create rental record
        Rentals rental = new Rentals();
        rental.setUser_id(userId);
        rental.setBikeId(bikeId);
        rental.setStartTime(LocalDateTime.now());
        rental.setCost(0.0);  // You may calculate the cost based on your business logic
        log.info("Bike with id {} successfully rented for user with id {}", bikeId, userId);
        return rentalRepository.save(rental);
    }


    @Transactional
    @Override
    public Rentals returnBike(Long rentalId) {
//        log.info("Attempting to return bike for rental with id {}", rentalId);
//        Rentals rental = rentalRepository.findById(rentalId)
//                .orElseThrow(() -> new ResourceNotFoundException("Rental", "id", rentalId));
//
//        // Update bike status to available
//        Bike bike = bikeRepository.findById(rental.getBikeId())
//                .orElseThrow(() -> new ResourceNotFoundException("Bike", "id", rental.getBikeId()));
//        bike.setRented(false);
//        bikeRepository.save(bike);
//
//        // Update rental end time and calculate cost
//        rental.setEndTime(LocalDateTime.now());
//        // Calculate cost based on your business logic
//        rental.setCost(calculateRentalCost(rental.getStartTime(), rental.getEndTime()));
//        log.info("Bike for rental with id {} successfully returned", rentalId);
//        return rentalRepository.save(rental);
        // Check if the rental exists
        Rentals rental = rentalRepository.findById(rentalId)
                .orElseThrow(() -> new ResourceNotFoundException("Rental not found with id: " + rentalId));

        // Check if the bike associated with the rental exists
        Bike bike = bikeRepository.findById(rental.getBikeId())
                .orElseThrow(() -> new ResourceNotFoundException("Bike not found with id: " + rental.getBikeId()));

        // Update bike status to not rented
        bike.setRented(false);
        bikeRepository.save(bike);

        // Update rental end time and calculate cost based on your business logic
        rental.setEndTime(LocalDateTime.now());
        rental.setCost(calculateRentalCost(rental.getStartTime(), rental.getEndTime())); // Implement your cost calculation logic

        return rentalRepository.save(rental);
    }

    private double calculateRentalCost(LocalDateTime startTime, LocalDateTime endTime) {
        long durationInHours = java.time.Duration.between(startTime, endTime).toHours();

        // Define hourly rate
        double hourlyRate = 5.0;

        // Calculate cost
        double cost = durationInHours * hourlyRate;

        return cost;
    }
}
