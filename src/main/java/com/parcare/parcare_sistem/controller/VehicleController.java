package com.parcare.parcare_sistem.controller;

import com.parcare.parcare_sistem.dto.VehicleDTO;
import com.parcare.parcare_sistem.model.Vehicle;
import com.parcare.parcare_sistem.repository.VehicleRepository;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/vehicles")
public class VehicleController {

    @Autowired
    private VehicleRepository vehicleRepository;

    @PostMapping
    public ResponseEntity<?> addVehicle(@RequestBody VehicleDTO vehicleDTO) {
        Vehicle vehicle = new Vehicle();
        vehicle.setLicensePlate(vehicleDTO.getLicense_plate());
        vehicle.setEntryTime(LocalDateTime.now());

        Vehicle saved = vehicleRepository.save(vehicle);


        return ResponseEntity.ok("Vehicul salvat: " + vehicleDTO.getLicense_plate());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteVehicle(@PathVariable Long id) {
        return vehicleRepository.findById(id)
                .map(vehicle -> {
                    vehicleRepository.delete(vehicle);

                    // Trimite notificare WebSocket

                    return ResponseEntity.ok("Vehicul șters cu succes.");
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping
    public List<Vehicle> getAllVehiclesSorted() {
        return vehicleRepository.findAllByOrderByEntryTimeDesc();
    }

    @GetMapping("/{plate}")
    public ResponseEntity<?> checkPlate(@PathVariable String plate) {
        boolean exists = vehicleRepository.existsByLicensePlateIgnoreCase(plate);
        if (exists) {
            return ResponseEntity.ok().build(); // 200 OK dacă există
        } else {
            return ResponseEntity.notFound().build(); // 404 dacă nu e în DB
        }
    }



}
