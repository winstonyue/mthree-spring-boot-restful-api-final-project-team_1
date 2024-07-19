package com.team1.currencyexchangeapi.service;


import com.team1.currencyexchangeapi.dao.UserDao;
import com.team1.currencyexchangeapi.model.Currency;
import com.team1.currencyexchangeapi.model.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService{

    private final UserDao userDao;

    @Autowired
    public UserServiceImpl(UserDao userDao){
        this.userDao = userDao;
    }

    @Override
    public List<User> getAllUser(){

        return userDao.getAllUser();

    }

    @Override
    public User addNewUser(User user){
        if(user.getUsername().isEmpty()){
            user.setUsername("Username is Empty. User not added");
            return user;
        }
        else if(user.getUsername().length() > 10){
            user.setUsername("Username cannot exceed 10 characters");
            return user;
        }
        List<User> userList = getAllUser();
        List<String> usernameList = userList.stream()
                .map(User::getUsername)
                .collect(Collectors.toList());
        if (usernameList.contains(user.getUsername())) {
            user.setUsername("Username already exists.");
        } else{
            try{
                return userDao.addNewUser(user);
            }catch(Exception e){
                user.setUsername("Unable to add new user.");
            }
        }
        return user;
    }

    @Override
    public User getUserByUsername(String username){
        User user = new User();
        List<User> userList = getAllUser();
        List<String> usernameList = userList.stream()
                .map(User::getUsername)
                .collect(Collectors.toList());
        if (!usernameList.contains(username)) {
            user.setUsername("Username does not exists.");
        } else {

            try {
                return userDao.getUserByUsername(username);
            } catch (Exception e) {
                user.setUsername("Unable to get user by username: " + username);
            }
        }
        return user;
    }

    @Override
    public User deleteUser(String username){
        User user = new User();
        List<User> userList = getAllUser();
        List<String> usernameList = userList.stream()
                .map(User::getUsername)
                .collect(Collectors.toList());
        if (!usernameList.contains(username)) {
            user.setUsername("Username does not exists.");
        } else {
            try {
                user = getUserByUsername(username);
                userDao.deleteUser(username);
                return user;
            } catch (Exception e) {
                user.setUsername("Unable to delete user by username: " + username);
            }
        }
        return user;
    }


    @Override
    public User updateUser(String username, User newUserInfo) {
        User user = new User();
        try{
            if(newUserInfo.getUsername().isEmpty()){
                user.setUsername("Username is Empty. User not added");
            }
            else if(newUserInfo.getUsername().length() > 10){
                user.setUsername("Username has too many characters");
            } else {
                userDao.updateUser(username, newUserInfo);
                return newUserInfo;
            }
        }catch (Exception e){
            user.setUsername("Unable to update user: " + username);
        }
        return user;
    }

}
