package com.smartpark.parkingmanagement.dto;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class ParkingLotDto {

	@NotBlank
	@Size(max = 50)
	private String lotId;

	@NotBlank
	private String location;

	@NotNull
	@Min(value = 0)
	private Integer capacity;

	@NotNull
	@Min(value = 0)
	private Integer occupiedSpaces;

	public ParkingLotDto() {
	}

	public String getLotId() {
		return lotId;
	}

	public void setLotId(String lotId) {
		this.lotId = lotId;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public Integer getCapacity() {
		return capacity;
	}

	public void setCapacity(Integer capacity) {
		this.capacity = capacity;
	}

	public Integer getOccupiedSpaces() {
		return occupiedSpaces;
	}

	public void setOccupiedSpaces(Integer occupiedSpaces) {
		this.occupiedSpaces = occupiedSpaces;
	}
}
