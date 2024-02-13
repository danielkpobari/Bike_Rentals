package com.bike_rentals.daniels_bike_services.exception;

public class ResourceNotFoundException extends RuntimeException{
    public ResourceNotFoundException(String message) {
        super(message);
    }

    public ResourceNotFoundException(String bike, String id, Long bikeId) {
        super(String.format("%s not found with %s : '%s'", bike, id, bikeId));
    }
}
