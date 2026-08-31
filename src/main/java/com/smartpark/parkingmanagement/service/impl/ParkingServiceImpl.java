package com.smartpark.parkingmanagement.service.impl;

import java.time.LocalDateTime;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.smartpark.parkingmanagement.dto.CheckInRequestDto;
import com.smartpark.parkingmanagement.dto.ParkingSessionResponseDto;
import com.smartpark.parkingmanagement.model.ParkingLot;
import com.smartpark.parkingmanagement.model.ParkingSession;
import com.smartpark.parkingmanagement.model.SessionStatus;
import com.smartpark.parkingmanagement.repository.*;
import com.smartpark.parkingmanagement.service.ParkingService;

@Service
public class ParkingServiceImpl implements ParkingService {
	private final ParkingSessionRepository parkingSessionRepository;
	private final ParkingLotRepository parkingLotRepository;
	private final VehicleRepository vehicleRepository;

	public ParkingServiceImpl(ParkingSessionRepository parkingSessionRepository,
			ParkingLotRepository parkingLotRepository, VehicleRepository vehicleRepository) {
		this.parkingSessionRepository = parkingSessionRepository;
		this.parkingLotRepository = parkingLotRepository;
		this.vehicleRepository = vehicleRepository;
	}

	@Override
	@Transactional
	public ParkingSessionResponseDto checkIn(CheckInRequestDto request) {
		if (!vehicleRepository.existsById(request.getLicensePlate())) {
			throw new IllegalArgumentException("Vehicle " + request.getLicensePlate() + " does not exist.");
		}

		if (parkingSessionRepository.existsByLicensePlateAndStatus(request.getLicensePlate(), SessionStatus.ACTIVE)) {
			throw new IllegalStateException(
					"Vehicle " + request.getLicensePlate() + " is already checked into an active parking session.");
		}

		ParkingLot parkingLot = parkingLotRepository.findById(request.getLotId()).orElseThrow(
				() -> new IllegalArgumentException("Parking lot " + request.getLotId() + " does not exist."));

		if (parkingLot.getOccupiedSpaces() >= parkingLot.getCapacity()) {
			throw new IllegalStateException("Parking lot " + request.getLotId() + " is full.");
		}

		parkingLot.setOccupiedSpaces(parkingLot.getOccupiedSpaces() + 1);
		parkingLotRepository.save(parkingLot);

		ParkingSession parkingSession = new ParkingSession();
		parkingSession.setLicensePlate(request.getLicensePlate());
		parkingSession.setLotId(request.getLotId());
		parkingSession.setCheckInTime(LocalDateTime.now());
		parkingSession.setStatus(SessionStatus.ACTIVE);
		ParkingSession savedSession = parkingSessionRepository.save(parkingSession);

		return mapToResponseDto(savedSession);
	}

	@Override
	@Transactional
	public ParkingSessionResponseDto checkOut(String licensePlate) {

		ParkingSession parkingSession = parkingSessionRepository
				.findByLicensePlateAndStatus(licensePlate, SessionStatus.ACTIVE).orElseThrow(
						() -> new IllegalStateException("No active parking session found for vehicle " + licensePlate));

		ParkingLot parkingLot = parkingLotRepository.findById(parkingSession.getLotId()).orElseThrow(
				() -> new IllegalArgumentException("Parking lot " + parkingSession.getLotId() + " does not exist."));

		if (parkingLot.getOccupiedSpaces() > 0) {
			parkingLot.setOccupiedSpaces(parkingLot.getOccupiedSpaces() - 1);
			parkingLotRepository.save(parkingLot);
		}

		parkingSession.setCheckOutTime(LocalDateTime.now());
		parkingSession.setStatus(SessionStatus.COMPLETED);
		ParkingSession updatedSession = parkingSessionRepository.save(parkingSession);

		return mapToResponseDto(updatedSession);
	}

	private ParkingSessionResponseDto mapToResponseDto(ParkingSession parkingSession) {
		ParkingSessionResponseDto dto = new ParkingSessionResponseDto();
		dto.setSessionId(parkingSession.getSessionId());
		dto.setLicensePlate(parkingSession.getLicensePlate());
		dto.setLotId(parkingSession.getLotId());
		dto.setCheckInTime(parkingSession.getCheckInTime());
		dto.setCheckOutTime(parkingSession.getCheckOutTime());
		dto.setStatus(parkingSession.getStatus());
		return dto;
	}

}
