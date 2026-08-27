package com.smartpark.parkingmanagement.model;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class ParkingLot {

    @NotNull
    @Size(max = 50)
    private String lotId;

    private String location;

    @Min(value = 0)
    private int capacity;

    @Min(value = 0)
    private int occupiedSpaces;

    public ParkingLot() {
    }

    public ParkingLot(String lotId, String location, int capacity, int occupiedSpaces) {
        this.lotId = lotId;
        this.location = location;
        this.capacity = capacity;
        this.occupiedSpaces = occupiedSpaces;
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

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public int getOccupiedSpaces() {
        return occupiedSpaces;
    }

    public void setOccupiedSpaces(int occupiedSpaces) {
        this.occupiedSpaces = occupiedSpaces;
    }
}