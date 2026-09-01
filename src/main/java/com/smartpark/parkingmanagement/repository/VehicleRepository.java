package com.smartpark.parkingmanagement.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.smartpark.parkingmanagement.model.Vehicle;

public interface VehicleRepository extends JpaRepository<Vehicle, String> {
	List<Vehicle> findByLicensePlateIn(List<String> licensePlates);
}
