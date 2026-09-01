package com.smartpark.parkingmanagement.service;

import java.util.List;

import com.smartpark.parkingmanagement.dto.ParkingLotOccupancyDto;
import com.smartpark.parkingmanagement.dto.ParkingLotDto;
import com.smartpark.parkingmanagement.dto.VehicleDto;

public interface ParkingLotService {
	ParkingLotDto registerParkingLot(ParkingLotDto parkingLotDto);

	ParkingLotOccupancyDto getOccupancyDetails(String lotId);

	List<VehicleDto> getCurrentlyParkedVehicles(String lotId);
}
