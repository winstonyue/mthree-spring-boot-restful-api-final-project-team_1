package com.team1.currencyexchangeapi.dao;

import com.team1.currencyexchangeapi.model.User;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.jdbc.DataJdbcTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DataJdbcTest
class UserDaoDBImplTest {

    private final JdbcTemplate jdbcTemplate;
    private final UserDao userDao;

    @Autowired
    public UserDaoDBImplTest(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
        userDao = new UserDaoDBImpl(jdbcTemplate);
    }

    @Test
    @DisplayName("Add New User Test")
    @Transactional
    public void addNewUserTest() {
        User user = new User();
        user.setUsername("NewUser");
        userDao.addNewUser(user);
        List<User> userList = userDao.getAllUser();

        assertNotNull(userList);
        assertEquals(3, userList.size());
    }

    @Test
    @DisplayName("Get All User Test")
    @Transactional
    public void getAllUserTest() {
        List<User> userList = userDao.getAllUser();

        assertNotNull(userList);
        assertEquals(2, userList.size());
    }

    @Test
    @DisplayName("Get User By Username: test")
    @Transactional
    public void getUserByUsernameTest() {
        User user = userDao.getUserByUsername("test");

        assertNotNull(user);
        assertEquals("test", user.getUsername());
    }

    @Test
    @DisplayName("Delete User By Username: test2")
    @Transactional
    public void deleteUserTest() {
        userDao.deleteUser("test2");

        assertEquals(1, userDao.getAllUser().size());
    }

    @Test
    @DisplayName("Update User Test")
    @Transactional
    public void updateUserTest() {
        User newUser = new User();
        newUser.setUsername("newUser");
        userDao.updateUser("test2", newUser);

        List<User> userList = userDao.getAllUser();
        assertEquals(2, userList.size());

        User retrievedUser = userDao.getUserByUsername("newUser");
        assertNotNull(retrievedUser);
    }
}
