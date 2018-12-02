package com.oocl.web.sampleWebApp.controllers;
import com.oocl.web.sampleWebApp.models.ParkingBoyResponse;

import com.oocl.web.sampleWebApp.domain.ParkingBoy;
import com.oocl.web.sampleWebApp.domain.ParkingBoyRepository;
import com.oocl.web.sampleWebApp.models.ParkingBoyResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

import static java.net.URI.create;

@RestController
@RequestMapping("/parkingboys")
public class ParkingBoyResource {

    @Autowired
    private ParkingBoyRepository parkingBoyRepository;

    String restLocation = "/parkingboys/";

    @GetMapping
    public ResponseEntity<ParkingBoyResponse[]> getAll() {
        final ParkingBoyResponse[] parkingBoys = parkingBoyRepository.findAll().stream()
            .map(ParkingBoyResponse::create)
            .toArray(ParkingBoyResponse[]::new);
        return ResponseEntity.ok(parkingBoys);
    }

    @PostMapping(produces = {"application/json"})
    public ResponseEntity<String> createParkingBoy(@RequestBody String employeeId) {
        if (parkingBoyRepository.findByEmployeeId(employeeId) != null){

            ParkingBoy parkingBoy = new ParkingBoy(employeeId);
            final ParkingBoy createdParkingBoy = parkingBoyRepository.save(parkingBoy);
            return ResponseEntity.created(create(restLocation + createdParkingBoy.getId())).build();
        }
        return ResponseEntity.status(HttpStatus.CONFLICT).build();
    }
}
