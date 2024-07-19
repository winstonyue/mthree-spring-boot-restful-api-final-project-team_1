package com.team1.currencyexchangeapi.dao;

import com.team1.currencyexchangeapi.model.Currency;

import java.util.List;

public interface CurrencyDao {

    List<Currency> getAllCurrency();

    Currency getCurrencyByCode(String currencyCode);

    Currency addCurrency(Currency currency);

    void updateCurrency(Currency currency);

    void deleteCurrencyByCode(String currencyCode);
}
