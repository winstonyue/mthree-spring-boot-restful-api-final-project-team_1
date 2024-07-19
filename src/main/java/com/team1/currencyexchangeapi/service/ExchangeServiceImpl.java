package com.team1.currencyexchangeapi.service;

import com.team1.currencyexchangeapi.dao.CurrencyDao;
import com.team1.currencyexchangeapi.dao.ExchangeDao;
import com.team1.currencyexchangeapi.dao.UserDao;
import com.team1.currencyexchangeapi.model.Currency;
import com.team1.currencyexchangeapi.model.Exchange;
import com.team1.currencyexchangeapi.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

// Marks this class as a Spring service component for handling exchange operations
@Service
public class ExchangeServiceImpl implements ExchangeService {

    private final ExchangeDao exchangeDao;
    private final UserDao userDao;
    private final CurrencyDao currencyDao;

    // Constructor-based dependency injection of ExchangeDao
    @Autowired
    public ExchangeServiceImpl(ExchangeDao exchangeDao, UserDao userDao, CurrencyDao currencyDao) {
        this.exchangeDao = exchangeDao;
        this.userDao = userDao;
        this.currencyDao = currencyDao;
    }

    // Adds a new exchange entry to the database
    @Override
    public Exchange addExchange(Exchange exchange) {
        boolean isValidate = validateExchange(exchange);
        if (isValidate) {
            try {
                return exchangeDao.addExchange(exchange);
            } catch (Exception e) {
                exchange.setUsername("Error adding exchange");
            }
        }
        return exchange;
    }

    // Retrieves all exchange entries from the database
    @Override
    public List<Exchange> getAllExchange() {
        try {
            return exchangeDao.getAllExchange();
        } catch (Exception e) {
            Exchange exchange = new Exchange();
            exchange.setUsername("Error retrieving all exchanges.");
            return new ArrayList<>((Collection) exchange);
        }
    }

    // Retrieves exchanges by username from the database
    @Override
    public List<Exchange> getExchangeByUsername(String username) {
        Exchange exchange = new Exchange();
        List<User> userList = userDao.getAllUser();
        List<String> usernameList = userList.stream()
                .map(User::getUsername)
                .collect(Collectors.toList());

        if (!usernameList.contains(username)) {
            exchange.setUsername("Username does not exist.");
        } else {
            try {
                return exchangeDao.getExchangeByUsername(username);
            } catch (Exception e) {
                exchange.setUsername("Error retrieving exchanges for username: " + username);
            }
        }
        return new ArrayList<>(List.of(exchange));
    }

    // Retrieves an exchange by its ID from the database
    @Override
    public Exchange getExchangeById(int id) {
        try {
            return exchangeDao.getExchangeById(id);
        } catch (Exception e) {
            Exchange exchange = new Exchange();
            exchange.setUsername("Error retrieving exchange with ID: " + id);
            return exchange;
        }
    }

    // Updates an existing exchange entry in the database
    @Override
    public Exchange updateExchange(Exchange exchange) {
        try {
            exchangeDao.updateExchange(exchange);
        } catch (Exception e) {
            exchange.setUsername("Error updating exchange: " + e.getMessage());
        }
        return exchange;
    }

    // Deletes an exchange entry from the database by its ID
    @Override
    public Exchange deleteExchangeById(int id) {
        Exchange exchange = new Exchange();
        try {
            exchange = getExchangeById(id);
            exchangeDao.deleteExchangeById(id);
        } catch (Exception e) {
            exchange.setUsername("Error deleting exchange with ID: " + id + ", " + e.getMessage());
        }
        return exchange;
    }

    public boolean validateExchange(Exchange exchange) {
        boolean isValidate = true;

        List<User> userList = userDao.getAllUser();
        List<String> usernameList = userList.stream()
                .map(User::getUsername)
                .collect(Collectors.toList());

        if (!usernameList.contains(exchange.getUsername())) {
            exchange.setUsername("Username does not exist.");
            isValidate = false;
        }

        List<Currency> currencyList = currencyDao.getAllCurrency();
        List<String> currencyCodes = currencyList.stream()
                .map(Currency::getCurrencyCode)
                .collect(Collectors.toList());
        if (!currencyCodes.contains(exchange.getFromCurrencyCode())) {
            exchange.setFromCurrencyCode("Currency code does not exist.");
            isValidate = false;
        }
        if (!currencyCodes.contains(exchange.getToCurrencyCode())) {
            exchange.setToCurrencyCode("Currency code does not exist.");
            isValidate = false;
        }
        if (exchange.getDate().isAfter(LocalDate.now())) {
            exchange.setUsername("Date cannot be future date.");
            isValidate = false;
        }
        if (exchange.getExchangeRate().compareTo(BigDecimal.ZERO) < 0) {
            exchange.setUsername("Exchange rate cannot be less than 0.");
            isValidate = false;
        }
        if (exchange.getBaseAmount().compareTo(BigDecimal.ZERO) <= 0) {
            exchange.setUsername("Base amount cannot be 0 or less.");
            isValidate = false;
        }
        if (exchange.getFinalAmount().compareTo(BigDecimal.ZERO) < 0) {
            exchange.setUsername("Final amount cannot be less than 0.");
            isValidate = false;
        }
        return isValidate;

    }
}
