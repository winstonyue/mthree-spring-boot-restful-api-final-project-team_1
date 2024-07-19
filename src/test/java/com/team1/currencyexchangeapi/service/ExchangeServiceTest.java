package com.team1.currencyexchangeapi.service;

import com.team1.currencyexchangeapi.dao.*;
import com.team1.currencyexchangeapi.model.Exchange;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.sql.Date;

import static org.junit.jupiter.api.Assertions.*;

public class ExchangeServiceTest {

    private ExchangeService exchangeService;

    public ExchangeServiceTest() {
        ExchangeDao exchangeDao = new ExchangeDaoStubImpl();
        UserDao userDao = new UserDaoStubImpl();
        CurrencyDao currencyDao = new CurrencyDaoStubImpl();
        exchangeService = new ExchangeServiceImpl(exchangeDao, userDao, currencyDao);
    }

    @Test
    @DisplayName("Find Exchange Service Test")
    public void findExchangeServiceTest() {
        // Add exchanges for testing
        Exchange expectedExchange1 = createExchange(1, "onlyUser", new Date(System.currentTimeMillis()), BigDecimal.valueOf(1.23), BigDecimal.valueOf(100), BigDecimal.valueOf(123), "USD", "EUR");
        exchangeService.addExchange(expectedExchange1);

        Exchange returnExchange = exchangeService.getExchangeById(expectedExchange1.getId());
        assertNotNull(returnExchange);
        assertEquals(expectedExchange1, returnExchange);
    }

    @Test
    @DisplayName("Exchange Not Found Service Test")
    public void exchangeNotFoundServiceTest() {
        Exchange notFound = exchangeService.getExchangeById(99);
        assertNull(notFound);
    }

    @Test
    @DisplayName("Exchange Add Service Test")
    public void exchangeAddServiceTest() {
        Exchange exchange = createExchange(2,"onlyUser", new Date(System.currentTimeMillis()), BigDecimal.valueOf(0.85), BigDecimal.valueOf(200), BigDecimal.valueOf(170), "EUR", "USD");

        Exchange newExchange = exchangeService.addExchange(exchange);
        assertNotNull(newExchange);
        assertEquals(exchange, newExchange);
    }

    private Exchange createExchange(int id, String username, Date date, BigDecimal exchangeRate, BigDecimal baseAmount, BigDecimal finalAmount, String fromCurrencyCode, String toCurrencyCode) {
        Exchange exchange = new Exchange();
        exchange.setId(id);
        exchange.setUsername(username);
        exchange.setDate(date.toLocalDate());
        exchange.setExchangeRate(exchangeRate);
        exchange.setBaseAmount(baseAmount);
        exchange.setFinalAmount(finalAmount);
        exchange.setFromCurrencyCode(fromCurrencyCode);
        exchange.setToCurrencyCode(toCurrencyCode);
        return exchange;
    }
}
