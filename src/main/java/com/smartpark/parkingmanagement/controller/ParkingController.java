package com.smartpark.parkingmanagement.controller;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.smartpark.parkingmanagement.dto.CheckInRequestDto;
import com.smartpark.parkingmanagement.dto.ParkingSessionResponseDto;
import com.smartpark.parkingmanagement.service.ParkingService;

@RestController
@RequestMapping("/api/parking")
public class ParkingController {

	private final ParkingService parkingService;

	public ParkingController(ParkingService parkingService) {
		this.parkingService = parkingService;
	}

	@PostMapping("/check-in")
	public ResponseEntity<ParkingSessionResponseDto> checkIn(@Valid @RequestBody CheckInRequestDto checkInRequestDto) {
		ParkingSessionResponseDto parkingSessionResponseDto = parkingService.checkIn(checkInRequestDto);
		return ResponseEntity.status(HttpStatus.CREATED).body(parkingSessionResponseDto);
	}

	@PostMapping("/check-out/{licensePlate}")
	public ResponseEntity<ParkingSessionResponseDto> checkOut(@PathVariable String licensePlate) {
		ParkingSessionResponseDto parkingSessionResponseDto = parkingService.checkOut(licensePlate);
		return ResponseEntity.status(HttpStatus.OK).body(parkingSessionResponseDto);
	}
	
	

}
