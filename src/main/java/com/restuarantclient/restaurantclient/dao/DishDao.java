package com.restuarantclient.restaurantclient.dao;

import com.restuarantclient.restaurantclient.model.Dish;

import java.util.List;

public interface DishDao {
    List<Dish> findAll();
    List<Dish> findByName(String name);
    void saveDish(Dish dish);
    void removeDishById(long id);
}
