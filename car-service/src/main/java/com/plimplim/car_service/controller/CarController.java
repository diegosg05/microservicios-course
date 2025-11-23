package com.plimplim.car_service.controller;

import com.plimplim.car_service.entity.Car;
import com.plimplim.car_service.service.CarService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RequiredArgsConstructor
@RequestMapping("/cars")
@RestController
public class CarController {

    private final CarService carService;

    @GetMapping
    public ResponseEntity<List<Car>> getAll() {
        var cars = carService.getAll();
        return ResponseEntity.ok(cars);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Car> getById(@PathVariable Integer id) {
        var car = carService.getCarById(id);
        if (car == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(car);
    }

    @PostMapping
    public ResponseEntity<Car> saveCar(@RequestBody Car car) {
        var carSaved = carService.saveCar(car);
        return ResponseEntity.created(URI.create("/cars/".concat(car.getId().toString()))).body(carSaved);
    }

    @GetMapping("/users/{userId}")
    public ResponseEntity<List<Car>> getCarsByUserId(@PathVariable Integer userId) {
        var cars = carService.byUserId(userId);
        return ResponseEntity.ok(cars);
    }
}
