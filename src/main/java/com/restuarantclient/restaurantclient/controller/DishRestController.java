package com.restuarantclient.restaurantclient.controller;

import com.restuarantclient.restaurantclient.dao.DishDao;
import com.restuarantclient.restaurantclient.model.Dish;
import com.restuarantclient.restaurantclient.service.DishService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/dishes")
public class DishRestController {

    private DishService dishService;

    private DishDao dishDao;

    @Autowired
    public DishRestController(DishService dishService, DishDao dishDao) {
        this.dishService = dishService;
        this.dishDao = dishDao;
    }

    @RequestMapping(method = RequestMethod.GET, value = "getDishes")
    public List<Dish> getDishes(){
        return dishDao.findAll();
    }

    @RequestMapping(method = RequestMethod.GET, value = "getDishes/{name}")
    public List<Dish> getDishesByName(@PathVariable String name){
        return dishDao.findByName(name);
    }

    @RequestMapping(method = RequestMethod.POST, value = "addDishes")
    public ResponseEntity addDish(@RequestBody Dish dish){
        try{
            dishDao.saveDish(dish);
            return new ResponseEntity(dish, HttpStatus.CREATED);
        } catch (Exception e){
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "removeDish/{id}")
    public boolean removeDishById(@PathVariable long id){
        Optional<Dish> dishById = dishDao.findAll().stream()
                .filter(dish -> dish.getDishId() == id)
                .findFirst();
        if (dishById.isPresent()){
            dishDao.removeDishById(id);
            return true;
        }
        return false;
    }
}
