package com.team1.currencyexchangeapi.dao;

import com.team1.currencyexchangeapi.dao.ExchangeDao;
import com.team1.currencyexchangeapi.model.Exchange;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

public class ExchangeDaoStubImpl implements ExchangeDao {

    private Exchange onlyExchange;

    public ExchangeDaoStubImpl() {
        onlyExchange = createExchange(1, "onlyUser", new Date(System.currentTimeMillis()), BigDecimal.valueOf(1.23), BigDecimal.valueOf(100), BigDecimal.valueOf(123), "USD", "EUR");
    }

    // Add a new exchange to the list
    @Override
    public Exchange addExchange(Exchange exchange) {
        return exchange;
    }

    // Retrieve all exchanges
    @Override
    public List<Exchange> getAllExchange() {
        return new ArrayList<>(List.of(onlyExchange));
    }

    // Retrieve exchanges by username
    @Override
    public List<Exchange> getExchangeByUsername(String username) {
        if (username.equals(onlyExchange.getUsername())) {
            return new ArrayList<>(List.of(onlyExchange));
        }
        return new ArrayList<>();
    }

    // Retrieve an exchange by its ID
    @Override
    public Exchange getExchangeById(int id) {
        if (onlyExchange.getId() == id) {
            return onlyExchange;
        }
        return null;
    }

    // Update an existing exchange
    @Override
    public void updateExchange(Exchange exchange) {
        // Stub method, no tests
    }

    // Delete an exchange by its ID
    @Override
    public void deleteExchangeById(int id) {
        // pass
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
