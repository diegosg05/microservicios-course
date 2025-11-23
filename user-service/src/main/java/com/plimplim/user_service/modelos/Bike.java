package com.plimplim.user_service.modelos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Bike {
    private String brand;
    private String model;
    private Integer userId;
}
