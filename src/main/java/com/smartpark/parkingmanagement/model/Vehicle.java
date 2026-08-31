package com.smartpark.parkingmanagement.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "vehicles")
public class Vehicle {

	@Id
	@Column(name = "license_plate", unique = true, nullable = false)
	private String licensePlate;

	@Enumerated(EnumType.STRING)
	@Column(name = "`type`", nullable = false)
	private VehicleType type;

	@Column(name = "owner_name", nullable = false)
	private String ownerName;

	public Vehicle() {
	}

	public Vehicle(String licensePlate, VehicleType type, String ownerName) {
		super();
		this.licensePlate = licensePlate;
		this.type = type;
		this.ownerName = ownerName;
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
