package com.smartpark.parkingmanagement.service.impl;

import org.springframework.stereotype.Service;

import com.smartpark.parkingmanagement.dto.VehicleDto;
import com.smartpark.parkingmanagement.model.Vehicle;
import com.smartpark.parkingmanagement.repository.VehicleRepository;
import com.smartpark.parkingmanagement.service.VehicleService;

@Service
public class VehicleServiceImpl implements VehicleService {

	private final VehicleRepository vehicleRepository;

	public VehicleServiceImpl(VehicleRepository vehicleRepository) {
		this.vehicleRepository = vehicleRepository;
	}

	@Override
	public VehicleDto registerVehicle(VehicleDto vehicleDto) {
		if (vehicleRepository.existsById(vehicleDto.getLicensePlate())) {
			throw new IllegalArgumentException(
					"Vehicle with this license plate" + vehicleDto.getLicensePlate() + "already exists.");
		}

		Vehicle vehicle = new Vehicle();
		vehicle.setLicensePlate(vehicleDto.getLicensePlate());
		vehicle.setType(vehicleDto.getType());
		vehicle.setOwnerName(vehicleDto.getOwnerName());

		Vehicle savedVehicle = vehicleRepository.save(vehicle);

		VehicleDto responseDto = new VehicleDto();
		responseDto.setLicensePlate(savedVehicle.getLicensePlate());
		responseDto.setType(savedVehicle.getType());
		responseDto.setOwnerName(savedVehicle.getOwnerName());

		return responseDto;
	}

}
