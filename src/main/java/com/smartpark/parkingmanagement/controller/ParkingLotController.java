package com.smartpark.parkingmanagement.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.smartpark.parkingmanagement.dto.ParkingLotOccupancyDto;
import com.smartpark.parkingmanagement.dto.VehicleDto;
import com.smartpark.parkingmanagement.dto.ParkingLotDto;
import com.smartpark.parkingmanagement.service.ParkingLotService;

@RestController
@RequestMapping("/api/parking-lots")
public class ParkingLotController {

	private final ParkingLotService parkingLotService;

	public ParkingLotController(ParkingLotService parkingLotService) {
		this.parkingLotService = parkingLotService;
	}

	@PostMapping
	public ResponseEntity<ParkingLotDto> registerParkingLot(@Valid @RequestBody ParkingLotDto parkingLotDto) {
		ParkingLotDto registeredParkingLot = parkingLotService.registerParkingLot(parkingLotDto);
		return ResponseEntity.status(HttpStatus.CREATED).body(registeredParkingLot);
	}
	
    @GetMapping("/{lotId}/occupancy")
    public ResponseEntity<ParkingLotOccupancyDto> getOccupancyDetails(@PathVariable String lotId) {
        ParkingLotOccupancyDto occupancyDetails = parkingLotService.getOccupancyDetails(lotId);
        return ResponseEntity.status(HttpStatus.OK).body(occupancyDetails);
    }

    @GetMapping("/{lotId}/vehicles")
    public ResponseEntity<List<VehicleDto>> getCurrentlyParkedVehicles(@PathVariable String lotId) {
        List<VehicleDto> vehicles = parkingLotService.getCurrentlyParkedVehicles(lotId);
        return ResponseEntity.status(HttpStatus.OK).body(vehicles);
    }
    
}
