package com.team1.currencyexchangeapi.dao;

import com.team1.currencyexchangeapi.model.Exchange;

import java.util.List;

public interface ExchangeDao {

    Exchange addExchange(Exchange exchange);

    List<Exchange> getAllExchange();

    List<Exchange> getExchangeByUsername(String username);

    Exchange getExchangeById(int id);

    void updateExchange(Exchange exchange);

    void deleteExchangeById(int id);

}
