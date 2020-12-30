package com.restuarantclient.restaurantclient.controller;

import com.restuarantclient.restaurantclient.dao.UserDao;
import com.restuarantclient.restaurantclient.model.Dish;
import com.restuarantclient.restaurantclient.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/users")
public class UserRestController {

    private UserDao userDao;

    @Autowired
    public UserRestController(UserDao userDao) {
        this.userDao = userDao;
    }

    @RequestMapping(method = RequestMethod.GET, value = "getUsers")
    public List<User> getUsers(){
        return userDao.findAll();
    }

    @RequestMapping(method = RequestMethod.POST, value = "addUser")
    public ResponseEntity addUser(@RequestBody User user){
        try{
            userDao.saveUser(user);
            return new ResponseEntity(user, HttpStatus.CREATED);
        } catch (Exception e){
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "removeUser/{id}")
    public boolean removeUserById(@PathVariable long id){
        Optional<User> userById = userDao.findAll().stream()
                .filter(user -> user.getUserId() == id)
                .findFirst();
        if (userById.isPresent()){
            userDao.removeUserById(id);
            return true;
        }
        return false;
    }
}
