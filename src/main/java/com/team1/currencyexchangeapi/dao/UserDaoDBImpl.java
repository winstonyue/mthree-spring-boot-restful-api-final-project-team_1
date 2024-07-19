package com.team1.currencyexchangeapi.dao;

import com.team1.currencyexchangeapi.dao.mappers.UserMapper;
import com.team1.currencyexchangeapi.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import java.util.List;

import java.util.List;

@Repository
public class UserDaoDBImpl implements UserDao{

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public UserDaoDBImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public User addNewUser(User user) {
        final String sql =
                "INSERT INTO users VALUES " +
                "(?);";

        jdbcTemplate.update(sql, user.getUsername());

        return user;
    }

    @Override
    public List<User> getAllUser() {
        final String sql =
                "SELECT username " +
                "FROM users;";

        return jdbcTemplate.query(sql, new UserMapper());
    }

    @Override
    public User getUserByUsername(String username) {
        final String sql =
                "SELECT username " +
                "FROM users " +
                "WHERE username = ?;";

        return jdbcTemplate.queryForObject(sql, new UserMapper(), username);
    }

    @Override
    public void deleteUser(String username) {
        final String sql =
                "DELETE FROM users " +
                "WHERE username = ?;";
        jdbcTemplate.update(sql, username);
    }

    @Override
    public void updateUser(String username, User newUserInfo) {
        final String sql =
                "UPDATE users SET " +
                "username = ? " +
                "WHERE username = ?;";
        jdbcTemplate.update(sql, newUserInfo.getUsername(), username);
    }
}
