package com.team1.currencyexchangeapi.dao;

import com.team1.currencyexchangeapi.dao.mappers.CurrencyMapper;
import com.team1.currencyexchangeapi.model.Currency;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class CurrencyDaoDBImpl implements CurrencyDao{

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public CurrencyDaoDBImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Currency> getAllCurrency() {
        final String sql =
                "SELECT currencyCode, currencyName " +
                "FROM currencies;";

        return jdbcTemplate.query(sql, new CurrencyMapper());
    }

    @Override
    public Currency getCurrencyByCode(String currencyCode) {
        final String sql =
                "SELECT currencyCode, currencyName " +
                "FROM currencies " +
                 "WHERE currencyCode = ?;";

        return jdbcTemplate.queryForObject(sql, new CurrencyMapper(), currencyCode);
    }

    @Override
    public Currency addCurrency(Currency currency) {
        final String sql =
                "INSERT INTO currencies(currencyCode, currencyName) " +
                "VALUES (?,?);";

        jdbcTemplate.update(
                sql,
                currency.getCurrencyCode(),
                currency.getCurrencyName()
        );

        return currency;
    }

    @Override
    public void updateCurrency(Currency currency) {
        final String sql =
                "UPDATE currencies SET " +
                "currencyName = ? " +
                "WHERE currencyCode = ?;";

        jdbcTemplate.update(
                sql,
                currency.getCurrencyName(),
                currency.getCurrencyCode()
        );
    }

    @Override
    public void deleteCurrencyByCode(String currencyCode) {
        final String sql =
                "DELETE FROM currencies " +
                "WHERE currencyCode = ?;";

        jdbcTemplate.update(sql, currencyCode);
    }
}
