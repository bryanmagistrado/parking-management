package com.smartpark.parkingmanagement.service.impl;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.smartpark.parkingmanagement.dto.ParkingLotDto;
import com.smartpark.parkingmanagement.model.ParkingLot;
import com.smartpark.parkingmanagement.repository.ParkingLotRepository;
import com.smartpark.parkingmanagement.service.ParkingLotService;

@Service
public class ParkingLotServiceImpl implements ParkingLotService {

	private final ParkingLotRepository parkingLotRepository;

	public ParkingLotServiceImpl(ParkingLotRepository parkingLotRepository) {
		this.parkingLotRepository = parkingLotRepository;
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

}
