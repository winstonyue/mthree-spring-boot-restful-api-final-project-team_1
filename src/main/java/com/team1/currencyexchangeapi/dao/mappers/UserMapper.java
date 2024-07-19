package com.team1.currencyexchangeapi.dao.mappers;

import com.team1.currencyexchangeapi.model.User;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserMapper implements RowMapper<User> {
    @Override
    public User mapRow(ResultSet rs, int rowNum) throws SQLException {
        User user = new User();

        user.setUsername(rs.getString("username"));

        return user;
    }
}