package com.smartpark.parkingmanagement.dto;

public class ParkingLotOccupancyDto {
	private final String lotId;
	private final int capacity;
	private final int occupiedSpaces;
	private final int availableSpaces;

	public ParkingLotOccupancyDto(String lotId, int capacity, int occupiedSpaces) {
		this.lotId = lotId;
		this.capacity = capacity;
		this.occupiedSpaces = occupiedSpaces;
		this.availableSpaces = capacity - occupiedSpaces;
	}

	public String getLotId() {
		return lotId;
	}

	public int getCapacity() {
		return capacity;
	}

	public int getOccupiedSpaces() {
		return occupiedSpaces;
	}

	public int getAvailableSpaces() {
		return availableSpaces;
	}
}
