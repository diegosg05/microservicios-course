package com.plimplim.bike_service.service;

import com.plimplim.bike_service.entity.Bike;
import com.plimplim.bike_service.repository.BikeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BikeService {

    private final BikeRepository bikeRepository;

    public List<Bike> getAll() {
        return bikeRepository.findAll();
    }

    public Bike getById(Integer id) {
        return bikeRepository.findById(id).orElse(null);
    }

    public Bike saveBike(Bike bike) {
        return bikeRepository.save(bike);
    }

    public List<Bike> byUserId(Integer userId) {
        return bikeRepository.findByUserId(userId);
    }
}
