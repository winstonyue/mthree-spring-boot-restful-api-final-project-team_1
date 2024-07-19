package com.team1.currencyexchangeapi.dao;

import com.team1.currencyexchangeapi.dao.UserDao;
import com.team1.currencyexchangeapi.model.User;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class UserDaoStubImpl implements UserDao {
    public User onlyUser;


    public UserDaoStubImpl() {
        onlyUser = new User();
        onlyUser.setUsername("onlyUser");

    }

    @Override
    public List<User> getAllUser() {
        return new ArrayList<>(List.of(onlyUser));
    }

    @Override
    public User addNewUser(User user){
        return user;
    }

    @Override
    public User getUserByUsername(String username){
        if (username.equals(onlyUser.getUsername())) {
            return onlyUser;
        }
        return null;
    }

    @Override
    public void updateUser(String username, User newUser){
        String newUsername = newUser.getUsername();
        if (newUsername.isEmpty()) {

        }
        else{
            onlyUser.setUsername(newUsername);
        }
    }



    @Override
    public void deleteUser(String username) {
        //Pass through method no tests
    }
}
