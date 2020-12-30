package com.restuarantclient.restaurantclient.dao;

import com.restuarantclient.restaurantclient.model.User;

import java.util.List;

public interface UserDao {
    List<User> findAll();
    void saveUser(User user);
    void removeUserById(long id);

}
