package com.smartpark.parkingmanagement.model;

public class Vehicle {

	private String licensePlate;
	
	private VehicleType vehicleType;
	
	private String ownerName;
	
	public Vehicle(String licensePlate, VehicleType vehicleType, String ownerName) {
		super();
		this.licensePlate = licensePlate;
		this.vehicleType = vehicleType;
		this.ownerName = ownerName;
	}

	public String getLicensePlate() {
		return licensePlate;
	}

	public void setLicensePlate(String licensePlate) {
		this.licensePlate = licensePlate;
	}

	public VehicleType getVehicleType() {
		return vehicleType;
	}

	public void setVehicleType(VehicleType vehicleType) {
		this.vehicleType = vehicleType;
	}

	public String getOwnerName() {
		return ownerName;
	}

	public void setOwnerName(String ownerName) {
		this.ownerName = ownerName;
	}
	
	
	
}
