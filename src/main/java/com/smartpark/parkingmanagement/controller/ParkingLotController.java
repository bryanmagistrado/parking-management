package com.smartpark.parkingmanagement.controller;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.smartpark.parkingmanagement.dto.ParkingLotDto;
import com.smartpark.parkingmanagement.service.ParkingLotService;

@RestController
@RequestMapping("/api/parking-lots")
public class ParkingLotController {
	
	private final ParkingLotService parkingLotService;

	public ParkingLotController(ParkingLotService parkingLotService) {
		this.parkingLotService = parkingLotService;
	}

	@PostMapping
	public ResponseEntity<ParkingLotDto> registerParkingLot(@Valid @RequestBody ParkingLotDto parkingLotDto) {
		ParkingLotDto registeredParkingLot = parkingLotService.registerParkingLot(parkingLotDto);
		return ResponseEntity.status(HttpStatus.CREATED).body(registeredParkingLot);
	}
	
	//The above is STS suggestion. Below is from Gemini
	/**
    @PostMapping
    public ResponseEntity<ParkingLotDto> registerParkingLot(@Valid @RequestBody ParkingLotDto dto) {
        ParkingLotDto savedLot = service.registerParkingLot(dto);
        return new ResponseEntity<>(savedLot, HttpStatus.CREATED);
    }
    
    La difference est que j'ai ajouté l'annotation @Valid pour valider le DTO avant de le passer au service. 
    Cela permet de s'assurer que les données reçues sont conformes aux contraintes définies dans le DTO 
    (par exemple, les champs obligatoires, les formats, etc.). 
    Si les données ne sont pas valides, Spring renverra automatiquement une réponse d'erreur appropriée sans appeler le service.
    
    */

}
