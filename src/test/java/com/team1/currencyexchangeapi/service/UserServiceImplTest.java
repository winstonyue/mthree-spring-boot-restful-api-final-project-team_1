package com.team1.currencyexchangeapi.service;

import com.team1.currencyexchangeapi.dao.UserDao;
import com.team1.currencyexchangeapi.dao.UserDaoStubImpl;
import com.team1.currencyexchangeapi.model.User;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UserServiceImplTest {
    private UserServiceImpl userService;


    public UserServiceImplTest(){
        UserDao userDao = new UserDaoStubImpl();
        userService = new UserServiceImpl(userDao);
    }

    @Test
    @DisplayName("Get User by Username Right")
    public void testRightGetUser(){
        User user = userService.getUserByUsername("onlyUser");
        assertNotNull(user);
        assertEquals("onlyUser", user.getUsername());

    }

    @Test
    @DisplayName("Get User by Username Wrong")
    public void testWrongGetUser(){
        User user = userService.getUserByUsername("wrongUsername");
        assertEquals("Username does not exists.", user.getUsername());

    }

    @Test
    @DisplayName("Add User by Right Username")
    public void testRightAddUser(){
        User user = new User();
        user.setUsername("newUser");

        User newUser = userService.addNewUser(user);

        assertNotNull(newUser);

        assertEquals("newUser",newUser.getUsername());
    }

    @Test
    @DisplayName("Add User by Wrong Username")
    public void testWrongAddUser(){
        User user = new User();
        user.setUsername("");

        User newUser = userService.addNewUser(user);

        assertEquals("Username is Empty. User not added", newUser.getUsername());

        user.setUsername("onlyUser");
        newUser = userService.addNewUser(user);
        assertEquals("Username already exists.", newUser.getUsername());

        user = new User();
        user.setUsername("VeryVeryVeryLongUsername");

        newUser = userService.addNewUser(user);
        assertEquals("Username cannot exceed 10 characters", newUser.getUsername());
    }

    @Test
    @DisplayName("Update User by Right Username")
    public void testRightUpdateUser(){
        User newUser = new User();
        newUser.setUsername("newUser");
        userService.updateUser("onlyUser", newUser);
        User user = userService.getUserByUsername("newUser");

        assertEquals("newUser", user.getUsername());

        newUser.setUsername("onlyUser");
        userService.updateUser("newUser", newUser);
        user = userService.getUserByUsername("onlyUser");

        assertEquals("onlyUser", user.getUsername());
    }




    @Test
    @DisplayName("Update User by Wrong Username")
    public void testWrongUpdateUser() {
        User emptyUser = new User();
        emptyUser.setUsername("");
        userService.updateUser("onlyUser", emptyUser);

        User user = userService.getUserByUsername(emptyUser.getUsername());
        assertEquals("Username does not exists.", user.getUsername());

        User longUser = new User();
        longUser.setUsername("VeryVeryVeryLongUsername");
        userService.updateUser("onlyUser", longUser);

        user = userService.getUserByUsername(longUser.getUsername());
        assertEquals("Username does not exists.", user.getUsername());


        user = userService.getUserByUsername("onlyUser");
        assertNotNull(user);
        assertEquals("onlyUser", user.getUsername());



    }

}