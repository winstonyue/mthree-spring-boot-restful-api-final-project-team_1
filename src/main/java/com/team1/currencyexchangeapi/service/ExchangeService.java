package com.team1.currencyexchangeapi.service;

import com.team1.currencyexchangeapi.model.Exchange;

import java.util.List;

// Interface defining methods for exchanging currencies
public interface ExchangeService {

    // Adds a new exchange entry
    Exchange addExchange(Exchange exchange);

    // Retrieves all exchange entries
    List<Exchange> getAllExchange();

    // Retrieves exchanges by username
    List<Exchange> getExchangeByUsername(String username);

    // Retrieves an exchange by its ID
    Exchange getExchangeById(int id);

    // Updates exchange
    Exchange updateExchange(Exchange exchange);

    // Deleted exchange by id
    Exchange deleteExchangeById(int id);
}
