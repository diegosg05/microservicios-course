package com.plimplim.user_service.service;

import com.plimplim.user_service.entity.User;
import com.plimplim.user_service.feignclients.BikeFeignClient;
import com.plimplim.user_service.feignclients.CarFeignClient;
import com.plimplim.user_service.modelos.Bike;
import com.plimplim.user_service.modelos.Car;
import com.plimplim.user_service.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class UserService {

    private final RestTemplate restTemplate;

    private final UserRepository userRepository;

    private final CarFeignClient carFeignClient;

    private final BikeFeignClient bikeFeignClient;

    public List<User> getAll() {
        return userRepository.findAll();
    }

    public User getUserById(Integer id) {
        return userRepository.findById(id).orElse(null);
    }

    public User saveUser(User user) {
        return userRepository.save(user);
    }

    public List<Car> getCars(Integer userId) {
       List<Car> cars = restTemplate.getForObject("http://localhost:8082/cars/users/".concat(userId.toString()), List.class);
       return cars;
    }

    public List<Bike> getBikes(Integer userId) {
        List<Bike> bikes = restTemplate.getForObject("http://localhost:8083/bikes/users/".concat(userId.toString()), List.class);
        return bikes;
    }

    public Car saveCar(Integer userId, Car car) {
        car.setUserId(userId);
        return carFeignClient.save(car);
    }

    public Bike saveBike(Integer userId, Bike bike) {
        bike.setUserId(userId);
        return bikeFeignClient.save(bike);
    }

    public Map<String, Object> getBikesAndCars(Integer userId) {
        Map<String, Object> results = new HashMap<>();
        User user = userRepository.findById(userId).orElse(null);
        if (user == null) {
            results.put("Message", "El usuario no existe");
            return results;
        }

        results.put("User", user);

        List<Car> cars = carFeignClient.getCars(userId);
        if (cars.isEmpty()) {
            results.put("Cars", "El usuario no tiene carros");
        } else {
            results.put("Cars", cars);
        }

        List<Bike> bikes = bikeFeignClient.getBikes(userId);
        if (bikes.isEmpty()) {
            results.put("Bikes", "El usuario no tiene motos");
        } else {
            results.put("Bikes", bikes);
        }

        return results;
    }
}
