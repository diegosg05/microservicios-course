package com.plimplim.user_service.feignclients;

import com.plimplim.user_service.modelos.Bike;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@FeignClient(name = "bike-service", url = "http://localhost:8083", path = "/bikes")
public interface BikeFeignClient {

    @PostMapping
    Bike save(Bike bike);

    @GetMapping("/users/{userId}")
    List<Bike> getBikes(@PathVariable Integer userId);
}
