package com.bike_rentals.daniels_bike_services.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "rental")
public class Rentals {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private Long user_id;
    private Long bikeId;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private double cost;
}
