package com.team1.currencyexchangeapi.dao;

import com.team1.currencyexchangeapi.model.User;

import java.util.List;

public interface UserDao {

    User addNewUser(User user);

    List<User> getAllUser();

    User getUserByUsername(String username);

    void deleteUser(String username);

    void updateUser(String username, User newUserInfo);

}
