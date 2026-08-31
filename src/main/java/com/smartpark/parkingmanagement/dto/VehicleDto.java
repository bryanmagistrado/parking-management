package com.smartpark.parkingmanagement.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import com.smartpark.parkingmanagement.model.VehicleType;

public class VehicleDto {

	@NotBlank
	@Pattern(regexp = "^[A-Za-z0-9-]+$")
	private String licensePlate;

	@NotNull
	private VehicleType type;

	@NotBlank
	@Pattern(regexp = "^[A-Za-z ]+$")
	private String ownerName;

	public VehicleDto() {
	}

	public String getLicensePlate() {
		return licensePlate;
	}

	public void setLicensePlate(String licensePlate) {
		this.licensePlate = licensePlate;
	}

	public VehicleType getType() {
		return type;
	}

	public void setType(VehicleType type) {
		this.type = type;
	}

	public String getOwnerName() {
		return ownerName;
	}

	public void setOwnerName(String ownerName) {
		this.ownerName = ownerName;
	}

}
