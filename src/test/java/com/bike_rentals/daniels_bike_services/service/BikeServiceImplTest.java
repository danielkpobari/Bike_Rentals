//package com.bike_rentals.daniels_bike_services.service;
//
//import com.bike_rentals.daniels_bike_services.model.Bike;
//import com.bike_rentals.daniels_bike_services.model.Rentals;
//import com.bike_rentals.daniels_bike_services.repository.BikeRepository;
//import com.bike_rentals.daniels_bike_services.repository.RentalRepository;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.MockitoAnnotations;
//
//
//import java.time.LocalDateTime;
//import java.util.Optional;
//
//import static org.junit.jupiter.api.Assertions.*;
//import static org.mockito.ArgumentMatchers.any;
//import static org.mockito.Mockito.*;
//import static org.mockito.Mockito.times;
//
//class BikeServiceImplTest {
//    @Mock
//    private BikeRepository bikeRepository;
//
//    @Mock
//    private RentalRepository rentalRepository;
//
//    @InjectMocks
//    private BikeServiceImpl bikeService;
//
//    @BeforeEach
//    void setUp() {
//        MockitoAnnotations.openMocks(this);
//    }
//
//    @Test
//    void testRentBike() {
//        Bike bike = new Bike();
//        bike.setId(1L);
//        when(bikeRepository.findById(1L)).thenReturn(java.util.Optional.of(bike));
//
//        // Mock the save method of rentalRepository
//        when(rentalRepository.save(any())).thenAnswer(invocation -> {
//            Rentals savedRental = invocation.getArgument(0);
//            assertNotNull(savedRental.getBikeId());  // Check if bikeId is not null
//            return savedRental;
//        });
//
//        // Invoke the service method
//        Rentals rental = bikeService.rentBike(1L, 1L);
//
//        assertNotNull(rental);
//        assertNotNull(rental.getBikeId());  // Check if bikeId is not null
//    }
//
//    @Test
//    public void testReturnBike() {
//        Rentals mockRental = new Rentals();
//        mockRental.setId(1L);
//        mockRental.setBikeId(1L);
//        mockRental.setUserId(1L);
//        mockRental.setStartTime(LocalDateTime.now());
//
//        Bike bike = new Bike();
//        bike.setId(1L);
//        bike.setRented(true);
//
//        when(rentalRepository.findById(1L)).thenReturn(Optional.of(mockRental));
//        when(bikeRepository.findById(1L)).thenReturn(Optional.of(bike));
//        when(rentalRepository.save(any(Rentals.class))).thenReturn(mockRental);
//
//        Rentals returnedRental = bikeService.returnBike(1L);
//
//        verify(rentalRepository, times(1)).findById(1L);
//        verify(bikeRepository, times(1)).findById(1L);
//        verify(bikeRepository, times(1)).save(bike);
//        verify(rentalRepository, times(1)).save(mockRental);
//
//        assertEquals(1L, returnedRental.getId());
//        assertEquals(1L, returnedRental.getBikeId());
//        assertEquals(1L, returnedRental.getUserId());
//    }

