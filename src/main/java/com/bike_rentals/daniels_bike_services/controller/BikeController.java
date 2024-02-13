package com.bike_rentals.daniels_bike_services.controller;

import com.bike_rentals.daniels_bike_services.model.Bike;
import com.bike_rentals.daniels_bike_services.service.BikeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/bikes")
public class BikeController {
    @Autowired
    private BikeService bikeService;

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/add")
    public Bike addBike(@Validated @RequestBody Bike bike) {
        return bikeService.addBike(bike);
    }

    @GetMapping("/all")
    public Iterable<Bike> getAllBikes() {
        return bikeService.getAllBikes();
    }

    @GetMapping("/user/{userId}")
    public Iterable<Bike> getUserSelection(@PathVariable Long userId) {
        return bikeService.getUserSelection(userId);
    }
}
