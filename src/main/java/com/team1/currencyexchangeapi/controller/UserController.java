package com.team1.currencyexchangeapi.controller;


import com.team1.currencyexchangeapi.model.Exchange;
import com.team1.currencyexchangeapi.model.User;
import com.team1.currencyexchangeapi.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
@CrossOrigin
public class UserController {

    @Autowired
    UserService userService;



    @GetMapping("/all")
    public List<User> getAllUser() {

        return userService.getAllUser();

    }

    @PostMapping("/add")
    public User addNewUser(@RequestBody User user) {

        return userService.addNewUser(user);

    }

    @GetMapping("/username/{username}")
    public User getUserByUsername(@PathVariable String username) {

        return userService.getUserByUsername(username);

    }

    @DeleteMapping("/{username}")
    public User deleteUser(@PathVariable String username){
        return userService.deleteUser(username);
    }


    @PutMapping("/{username}")
    public User updateUser(@PathVariable String username, @RequestBody User newUserInfo){
        return userService.updateUser(username, newUserInfo);
    }








}
