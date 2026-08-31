package com.smartpark.parkingmanagement.dto;

import javax.validation.constraints.NotBlank;

public class CheckInRequestDto {
	@NotBlank
	private String licensePlate;

	@NotBlank
	private String lotId;

	public String getLicensePlate() {
		return licensePlate;
	}

	public void setLicensePlate(String licensePlate) {
		this.licensePlate = licensePlate;
	}

	public String getLotId() {
		return lotId;
	}

	public void setLotId(String lotId) {
		this.lotId = lotId;
	}
}
