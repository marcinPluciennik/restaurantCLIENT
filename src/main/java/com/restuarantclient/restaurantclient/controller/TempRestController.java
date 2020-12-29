package com.restuarantclient.restaurantclient.controller;

import com.restuarantclient.restaurantclient.model.Temp;
import com.restuarantclient.restaurantclient.repository.TempDaoImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/temps")
public class TempRestController {

    private TempDaoImpl tempDao;

    @Autowired
    public TempRestController(TempDaoImpl tempDao) {
        this.tempDao = tempDao;
    }

    @RequestMapping(method = RequestMethod.GET, value = "getTemp")
    public Temp getTempToday(){
        return tempDao.findTempToday();
    }
}
