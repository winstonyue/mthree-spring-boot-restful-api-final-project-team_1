package com.team1.currencyexchangeapi.dao;

import com.team1.currencyexchangeapi.model.Currency;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class CurrencyDaoStubImpl implements CurrencyDao {

    private final List<Currency> currencies = new ArrayList<>();

    public CurrencyDaoStubImpl() {
        Currency usd = new Currency();
        usd.setCurrencyCode("USD");
        usd.setCurrencyName("United States Dollar");
        currencies.add(usd);

        Currency eur = new Currency();
        eur.setCurrencyCode("EUR");
        eur.setCurrencyName("Euro");
        currencies.add(eur);
    }

    @Override
    public List<Currency> getAllCurrency() {
        return new ArrayList<>(currencies);
    }

    @Override
    public Currency getCurrencyByCode(String currencyCode) {
        return currencies.stream()
                .filter(currency -> currency.getCurrencyCode().equalsIgnoreCase(currencyCode))
                .findFirst()
                .orElse(null);
    }

    @Override
    public Currency addCurrency(Currency currency) {
        currencies.add(currency);
        return currency;
    }

    @Override
    public void updateCurrency(Currency currency) {
        currencies.stream()
                .filter(c -> c.getCurrencyCode().equalsIgnoreCase(currency.getCurrencyCode()))
                .findFirst()
                .ifPresent(c -> c.setCurrencyName(currency.getCurrencyName()));
    }

    @Override
    public void deleteCurrencyByCode(String currencyCode) {
        currencies.removeIf(currency -> currency.getCurrencyCode().equalsIgnoreCase(currencyCode));
    }
}
