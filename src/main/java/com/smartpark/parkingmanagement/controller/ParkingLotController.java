package com.smartpark.parkingmanagement.controller;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

}
