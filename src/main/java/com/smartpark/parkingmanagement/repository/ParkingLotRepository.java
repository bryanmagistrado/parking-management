package com.smartpark.parkingmanagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.smartpark.parkingmanagement.model.ParkingLot;

public interface ParkingLotRepository extends JpaRepository<ParkingLot, String> {
}
