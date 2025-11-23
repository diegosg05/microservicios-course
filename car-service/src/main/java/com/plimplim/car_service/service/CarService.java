package com.plimplim.car_service.service;

import com.plimplim.car_service.entity.Car;
import com.plimplim.car_service.repository.CarRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CarService {

    private final CarRepository carRepository;

    public List<Car> getAll() {
        return carRepository.findAll();
    }

    public Car getCarById(Integer id) {
        return carRepository.findById(id).orElse(null);
    }

    public Car saveCar(Car car) {
        return carRepository.save(car);
    }

    public List<Car> byUserId(Integer userId) {
        return carRepository.findByUserId(userId);
    }
}
