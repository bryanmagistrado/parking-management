package com.smartpark.parkingmanagement.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.smartpark.parkingmanagement.model.ParkingSession;
import com.smartpark.parkingmanagement.model.SessionStatus;

public interface ParkingSessionRepository extends JpaRepository<ParkingSession, Long> {
    boolean existsByLicensePlateAndStatus(String licensePlate, SessionStatus status);
    Optional<ParkingSession> findByLicensePlateAndStatus(String licensePlate, SessionStatus status);
}
