package com.restuarantclient.restaurantclient.service;

import com.restuarantclient.restaurantclient.model.Dish;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

@Service
public class DishService {

    private RestTemplate restTemplate;

    public DishService() {
        this.restTemplate = new RestTemplate();
    }

    public Long convertToLong(Object o){
        String stringToConvert = String.valueOf(o);
        Long convertedLong = Long.parseLong(stringToConvert);
        return convertedLong;
    }
}
