package com.team1.currencyexchangeapi.dao;

import com.team1.currencyexchangeapi.model.Exchange;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.jdbc.DataJdbcTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DataJdbcTest
class ExchangeDaoDBImplTest {

    private final JdbcTemplate jdbcTemplate;
    private final ExchangeDao exchangeDao;

    @Autowired
    public ExchangeDaoDBImplTest(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
        exchangeDao = new ExchangeDaoDBImpl(jdbcTemplate);
    }

    @Test
    @DisplayName("Add New Exchange Test")
    @Transactional
    public void addNewExchangeTest() {
        Exchange exchange = new Exchange();
        exchange.setUsername("test2");
        exchange.setFromCurrencyCode("usd");
        exchange.setToCurrencyCode("eur");
        exchange.setDate(LocalDate.parse("2024-04-07"));
        exchange.setExchangeRate(BigDecimal.valueOf(0.92250728));
        exchange.setBaseAmount(BigDecimal.valueOf(500));
        exchange.calculateFinalAmount();
        exchangeDao.addExchange(exchange);
        List<Exchange> exchangeList = exchangeDao.getAllExchange();

        assertNotNull(exchangeList);
        assertEquals(3, exchangeList.size());
    }

    @Test
    @DisplayName("Get All Exchange Test")
    @Transactional
    public void getAllExchangeTest() {
        List<Exchange> exchangeList = exchangeDao.getAllExchange();

        assertNotNull(exchangeList);
        assertEquals(2, exchangeList.size());
    }

    @Test
    @DisplayName("Get Exchange By Username: test")
    @Transactional
    public void getExchangeByUsernameTest() {
        List<Exchange> userExchangeList = exchangeDao.getExchangeByUsername("test");

        assertNotNull(userExchangeList);
        assertEquals(1, userExchangeList.size());
        assertEquals("test", userExchangeList.get(0).getUsername());
    }

    @Test
    @DisplayName("Get Exchange By Id: 1")
    @Transactional
    public void getExchangeByIdTest() {
        Exchange exchange = exchangeDao.getExchangeById(1);

        assertNotNull(exchange);
        assertEquals(1, exchange.getId());
    }

    @Test
    @DisplayName("Update Exchange Test")
    @Transactional
    public void updateExchangeTest() {
        Exchange exchange = new Exchange();
        exchange.setUsername("test2");
        exchange.setFromCurrencyCode("usd");
        exchange.setToCurrencyCode("eur");
        exchange.setDate(LocalDate.parse("2024-04-07"));
        exchange.setExchangeRate(BigDecimal.valueOf(0.92250728));
        exchange.setBaseAmount(BigDecimal.valueOf(500));
        exchange.calculateFinalAmount();
        exchangeDao.addExchange(exchange);

        int id = exchange.getId();

        Exchange editedExchange = new Exchange();
        editedExchange.setId(id);
        editedExchange.setUsername("test2");
        editedExchange.setFromCurrencyCode("eur");
        editedExchange.setToCurrencyCode("usd");
        editedExchange.setDate(LocalDate.parse("2024-04-08"));
        editedExchange.setExchangeRate(BigDecimal.valueOf(1.08301095));
        editedExchange.setBaseAmount(BigDecimal.valueOf(600));
        editedExchange.calculateFinalAmount();
        exchangeDao.updateExchange(editedExchange);

        List<Exchange> exchangeList = exchangeDao.getAllExchange();

        assertNotNull(exchangeList);
        assertEquals(3, exchangeList.size());

        Exchange retrievedExchange = exchangeDao.getExchangeById(id);
        assertEquals(editedExchange, retrievedExchange);
        assertNotEquals(exchange, retrievedExchange);
    }

    @Test
    @DisplayName("Delete Exchange: 2")
    @Transactional
    public void deleteExchangeTest() {
        exchangeDao.deleteExchangeById(2);
        List<Exchange> exchangeList = exchangeDao.getAllExchange();

        assertNotNull(exchangeList);
        assertEquals(1, exchangeList.size());
    }

}