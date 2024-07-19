package com.team1.currencyexchangeapi.dao.mappers;

import com.team1.currencyexchangeapi.model.Currency;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CurrencyMapper implements RowMapper<Currency> {
    @Override
    public Currency mapRow(ResultSet rs, int rowNum) throws SQLException {
        Currency currency = new Currency();

        currency.setCurrencyCode(rs.getString("currencyCode"));
        currency.setCurrencyName(rs.getString("currencyName"));

        return currency;
    }
}
