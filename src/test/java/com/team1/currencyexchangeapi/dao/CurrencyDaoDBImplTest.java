package com.team1.currencyexchangeapi.dao;

import com.team1.currencyexchangeapi.model.Currency;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.jdbc.DataJdbcTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DataJdbcTest
class CurrencyDaoDBImplTest {

    private final JdbcTemplate jdbcTemplate;
    private final CurrencyDao currencyDao;

    @Autowired
    public CurrencyDaoDBImplTest(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
        currencyDao = new CurrencyDaoDBImpl(jdbcTemplate);
    }

    @Test
    @DisplayName("Get All Currency Test")
    @Transactional
    public void getAllCurrencyTest() {
        List<Currency> currencyList = currencyDao.getAllCurrency();

        assertNotNull(currencyList);
        assertEquals(6, currencyList.size());
    }

    @Test
    @DisplayName("Get Currency By Code: usd")
    @Transactional
    public void getCurrencyByCodeTest() {
        Currency currency = currencyDao.getCurrencyByCode("usd");

        assertNotNull(currency);
        assertEquals("usd", currency.getCurrencyCode());
    }

    @Test
    @DisplayName("Add New Currency Test")
    @Transactional
    public void addNewCurrencyTest() {
        Currency currency = new Currency();
        currency.setCurrencyCode("new");
        currency.setCurrencyName("New Currency");
        currencyDao.addCurrency(currency);
        List<Currency> currencyList = currencyDao.getAllCurrency();

        assertNotNull(currencyList);
        assertEquals(7, currencyList.size());
    }

    @Test
    @DisplayName("Update Currency Test")
    @Transactional
    public void updateCurrencyTest() {
        Currency currency = new Currency();
        currency.setCurrencyCode("new");
        currency.setCurrencyName("New Currency");
        currencyDao.addCurrency(currency);

        Currency editedCurrency = new Currency();
        editedCurrency.setCurrencyCode("new");
        editedCurrency.setCurrencyName("Edited Currency");
        currencyDao.updateCurrency(editedCurrency);

        List<Currency> currencyList = currencyDao.getAllCurrency();
        Currency retrievedCurrency = currencyDao.getCurrencyByCode("new");

        assertNotNull(currencyList);
        assertEquals(7, currencyList.size());
        assertEquals(editedCurrency, retrievedCurrency);
        assertNotEquals(currency, retrievedCurrency);
    }

    @Test
    @DisplayName("Delete Currency Test: usd")
    @Transactional
    public void deleteCurrencyTest() {
        currencyDao.deleteCurrencyByCode("usd");
        List<Currency> currencyList = currencyDao.getAllCurrency();

        assertNotNull(currencyList);
        assertEquals(5, currencyList.size());

    }

}