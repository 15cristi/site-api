package com.parcare.parcare_sistem.repository;

import com.parcare.parcare_sistem.model.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;


public interface VehicleRepository extends JpaRepository<Vehicle, Long> {

    List<Vehicle> findAllByLicensePlate(String licensePlate);
    boolean existsByLicensePlateIgnoreCase(String licensePlate);
    List<Vehicle> findAllByOrderByEntryTimeDesc();

}

