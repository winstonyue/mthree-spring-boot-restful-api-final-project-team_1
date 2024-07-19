package com.team1.currencyexchangeapi.dao.mappers;

import com.team1.currencyexchangeapi.model.Exchange;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ExchangeMapper implements RowMapper<Exchange> {
    @Override
    public Exchange mapRow(ResultSet rs, int rowNum) throws SQLException {
        Exchange exchange = new Exchange();

        exchange.setId(rs.getInt("exchangeId"));
        exchange.setUsername(rs.getString("username"));
        exchange.setFromCurrencyCode(rs.getString("fromCurrencyCode"));
        exchange.setToCurrencyCode(rs.getString("toCurrencyCode"));
        exchange.setDate(rs.getDate("exchangeDate").toLocalDate());
        exchange.setExchangeRate(rs.getBigDecimal("exchangeRate"));
        exchange.setBaseAmount(rs.getBigDecimal("baseAmount"));
        exchange.setFinalAmount(rs.getBigDecimal("finalAmount"));

        return exchange;
    }
}
