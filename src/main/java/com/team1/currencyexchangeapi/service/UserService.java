package com.team1.currencyexchangeapi.service;

import com.team1.currencyexchangeapi.model.User;

import java.util.List;

public interface UserService {

    User addNewUser(User user);

    List<User> getAllUser();

    User getUserByUsername(String username);

    User deleteUser(String username);

    User updateUser(String username, User newUserInfo);
}
