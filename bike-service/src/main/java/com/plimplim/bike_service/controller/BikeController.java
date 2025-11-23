package com.plimplim.bike_service.controller;

import com.plimplim.bike_service.entity.Bike;
import com.plimplim.bike_service.service.BikeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/bikes")
public class BikeController {

    private final BikeService bikeService;

    @GetMapping
    public ResponseEntity<List<Bike>> getAll() {
        var bikes = bikeService.getAll();
        return ResponseEntity.ok(bikes);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Bike> getById(@PathVariable Integer id) {
        var bike = bikeService.getById(id);
        if (bike == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(bike);
    }

    @PostMapping
    public ResponseEntity<Bike> saveBike(@RequestBody Bike bike) {
        var bikeSaved = bikeService.saveBike(bike);
        return ResponseEntity.created(URI.create("/bikes/".concat(bikeSaved.getId().toString()))).body(bikeSaved);
    }

    @GetMapping("/users/{userId}")
    public ResponseEntity<List<Bike>> getByUserId(@PathVariable Integer userId) {
        var bikes = bikeService.byUserId(userId);
        return ResponseEntity.ok(bikes);
    }

}
