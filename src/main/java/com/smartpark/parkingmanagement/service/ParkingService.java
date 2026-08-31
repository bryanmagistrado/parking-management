package com.smartpark.parkingmanagement.service;

import com.smartpark.parkingmanagement.dto.CheckInRequestDto;
import com.smartpark.parkingmanagement.dto.ParkingSessionResponseDto;

public interface ParkingService {
    ParkingSessionResponseDto checkIn(CheckInRequestDto request);
    ParkingSessionResponseDto checkOut(String licensePlate);
}
