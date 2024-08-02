package com.example.flight_status.repository;


import com.example.flight_status.model.Flight;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FlightRepository extends JpaRepository<Flight, Long> {
    // Custom queries can be defined here if needed
}
