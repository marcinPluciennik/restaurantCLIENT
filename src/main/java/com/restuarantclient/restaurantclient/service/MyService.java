package com.restuarantclient.restaurantclient.service;

import org.springframework.stereotype.Service;

@Service
public class MyService {

    public Long convertToLong(Object o){
        String stringToConvert = String.valueOf(o);
        Long convertedLong = Long.parseLong(stringToConvert);
        return convertedLong;
    }
}
