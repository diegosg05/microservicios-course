package com.plimplim.user_service.controller;

import com.plimplim.user_service.entity.User;
import com.plimplim.user_service.modelos.Bike;
import com.plimplim.user_service.modelos.Car;
import com.plimplim.user_service.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping
    public ResponseEntity<List<User>> listUsers() {
        var users = userService.getAll();
        return ResponseEntity.ok(users);
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable Integer id) {
        var user = userService.getUserById(id);
        if (user == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(user);
    }

    @PostMapping
    public ResponseEntity<User> saveUser(@RequestBody User user) {
        var userSaved = userService.saveUser(user);
        return ResponseEntity.created(URI.create("/users/".concat(userSaved.getId().toString()))).body(userSaved);
    }

    @GetMapping("/cars/{userId}")
    public ResponseEntity<List<Car>> getCars(@PathVariable Integer userId) {
        var user = userService.getUserById(userId);
        if (user == null) {
            return ResponseEntity.notFound().build();
        }
        var cars = userService.getCars(userId);
        return ResponseEntity.ok(cars);
    }

    @GetMapping("/bikes/{userId}")
    public ResponseEntity<List<Bike>> getBikes(@PathVariable Integer userId) {
        var user = userService.getUserById(userId);
        if (user == null) {
            return ResponseEntity.notFound().build();
        }
        var bikes = userService.getBikes(userId);
        return ResponseEntity.ok(bikes);
    }

    @PostMapping("/cars/{userId}")
    public ResponseEntity<Car> saveCar(@PathVariable Integer userId, @RequestBody Car car) {
        Car carSaved = userService.saveCar(userId, car);
        return ResponseEntity.ok(carSaved);
    }

    @PostMapping("/bikes/{userId}")
    public ResponseEntity<Bike> saveBike(@PathVariable Integer userId, @RequestBody Bike bike) {
        Bike bikeSaved = userService.saveBike(userId, bike);
        return ResponseEntity.ok(bikeSaved);
    }

    @GetMapping("/all/{userId}")
    public ResponseEntity<Map<String, Object>> getBikesAndCars(@PathVariable Integer userId) {
        var results = userService.getBikesAndCars(userId);
        return ResponseEntity.ok(results);
    }
}
