package com.team1.currencyexchangeapi.service;

import com.team1.currencyexchangeapi.model.Currency;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

public interface CurrencyService {

    List<Currency> getAllCurrency();

    Currency getCurrencyByCode(String currencyCode);

    Currency addCurrency(Currency currency);

    Currency updateCurrency(Currency currency);

    Currency deleteCurrencyByCode(String currencyCode);

//    BigDecimal getExchangeRate(String fromCurrency, String toCurrency, String date);
}
