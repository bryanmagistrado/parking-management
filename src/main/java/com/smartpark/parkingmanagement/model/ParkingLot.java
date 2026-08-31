package com.smartpark.parkingmanagement.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name = "parking_lots")
public class ParkingLot {

	@Id
    @Column(name = "lot_id", nullable = false, length = 50)
    private String lotId;
	
    @Column(name = "location", nullable = false, length = 255)
    private String location;
    
    @Column(name = "capacity", nullable = false)
    private Integer capacity;
    
    @Column(name = "occupied_spaces", nullable = false)
    private Integer occupiedSpaces;

    public ParkingLot() {
    }

    public ParkingLot(String lotId, String location, Integer capacity, Integer occupiedSpaces) {
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