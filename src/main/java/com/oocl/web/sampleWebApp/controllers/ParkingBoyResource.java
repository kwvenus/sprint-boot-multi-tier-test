package com.oocl.web.sampleWebApp.controllers;
import com.oocl.web.sampleWebApp.models.ParkingBoyResponse;

import com.oocl.web.sampleWebApp.domain.ParkingBoy;
import com.oocl.web.sampleWebApp.domain.ParkingBoyRepository;
import com.oocl.web.sampleWebApp.models.ParkingBoyResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<String> createParkingBoy(@RequestBody ParkingBoy parkingBoy) {
        final ParkingBoy createdParkingBoy = parkingBoyRepository.save(parkingBoy);
        parkingBoyRepository.flush();

        return ResponseEntity.created(create(restLocation + createdParkingBoy.getId())).build();

    }
}
