package com.smartpark.parkingmanagement.service.impl;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.smartpark.parkingmanagement.dto.ParkingLotOccupancyDto;
import com.smartpark.parkingmanagement.dto.ParkingLotDto;
import com.smartpark.parkingmanagement.dto.VehicleDto;
import com.smartpark.parkingmanagement.model.ParkingLot;
import com.smartpark.parkingmanagement.model.ParkingSession;
import com.smartpark.parkingmanagement.model.SessionStatus;
import com.smartpark.parkingmanagement.model.Vehicle;
import com.smartpark.parkingmanagement.repository.ParkingLotRepository;
import com.smartpark.parkingmanagement.repository.ParkingSessionRepository;
import com.smartpark.parkingmanagement.repository.VehicleRepository;
import com.smartpark.parkingmanagement.service.ParkingLotService;

@Service
public class ParkingLotServiceImpl implements ParkingLotService {

	private final ParkingLotRepository parkingLotRepository;
	private final ParkingSessionRepository sessionRepository;
	private final VehicleRepository vehicleRepository;

	public ParkingLotServiceImpl(ParkingLotRepository parkingLotRepository, ParkingSessionRepository sessionRepository,
			VehicleRepository vehicleRepository) {
		this.parkingLotRepository = parkingLotRepository;
		this.sessionRepository = sessionRepository;
		this.vehicleRepository = vehicleRepository;
	}

	@Override
	@Transactional
	public ParkingLotDto registerParkingLot(ParkingLotDto parkingLotDto) {

		if (parkingLotRepository.existsById(parkingLotDto.getLotId())) {
			throw new IllegalArgumentException(
					"Parking lot with ID '" + parkingLotDto.getLotId() + "' is already registered.");
		}

		if (parkingLotDto.getOccupiedSpaces() > parkingLotDto.getCapacity()) {
			throw new IllegalArgumentException("Occupied spaces cannot exceed capacity.");
		}

		ParkingLot parkingLot = new ParkingLot();

		parkingLot.setLotId(parkingLotDto.getLotId());
		parkingLot.setLocation(parkingLotDto.getLocation());
		parkingLot.setCapacity(parkingLotDto.getCapacity());
		parkingLot.setOccupiedSpaces(parkingLotDto.getOccupiedSpaces());

		ParkingLot savedParkingLot = parkingLotRepository.save(parkingLot);

		ParkingLotDto responseDto = new ParkingLotDto();
		responseDto.setLotId(savedParkingLot.getLotId());
		responseDto.setLocation(savedParkingLot.getLocation());
		responseDto.setCapacity(savedParkingLot.getCapacity());
		responseDto.setOccupiedSpaces(savedParkingLot.getOccupiedSpaces());

		return responseDto;
	}

	@Override
	@Transactional
	public ParkingLotOccupancyDto getOccupancyDetails(String lotId) {
		ParkingLot parkingLot = parkingLotRepository.findById(lotId)
				.orElseThrow(() -> new IllegalArgumentException("Parking lot not found with ID: " + lotId));

		return new ParkingLotOccupancyDto(parkingLot.getLotId(), parkingLot.getCapacity(),
				parkingLot.getOccupiedSpaces());

	}

	@Override
	public List<VehicleDto> getCurrentlyParkedVehicles(String lotId) {

		if (!parkingLotRepository.existsById(lotId)) {
			throw new IllegalArgumentException("Parking lot not found with ID: " + lotId);
		}

		List<ParkingSession> activeSessions = sessionRepository.findByLotIdAndStatus(lotId, SessionStatus.ACTIVE);

		List<String> activePlates = activeSessions.stream().map(session -> session.getLicensePlate())
				.collect(Collectors.toList());

		if (activePlates.isEmpty()) {
			return Collections.emptyList();
		}

		List<Vehicle> vehicles = vehicleRepository.findByLicensePlateIn(activePlates);

		return vehicles.stream().map(v -> {
			VehicleDto vehicleDto = new VehicleDto();
			vehicleDto.setLicensePlate(v.getLicensePlate());
			vehicleDto.setType(v.getType());
			vehicleDto.setOwnerName(v.getOwnerName());
			return vehicleDto;
		}).collect(Collectors.toList());
	}

}
