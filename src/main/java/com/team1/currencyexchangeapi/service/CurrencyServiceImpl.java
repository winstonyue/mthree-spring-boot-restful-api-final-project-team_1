package com.team1.currencyexchangeapi.service;

import com.team1.currencyexchangeapi.dao.CurrencyDao;
import com.team1.currencyexchangeapi.model.Currency;
import com.team1.currencyexchangeapi.service.CurrencyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CurrencyServiceImpl implements CurrencyService {

    private final CurrencyDao currencyDao;

    @Autowired
    public CurrencyServiceImpl(CurrencyDao currencyDao) {
        this.currencyDao = currencyDao;
    }

    @Override
    public List<Currency> getAllCurrency() {
        return currencyDao.getAllCurrency();
    }

    @Override
    public Currency getCurrencyByCode(String currencyCode) {
        Currency currency = new Currency();
        if (currencyCode.isEmpty()) {
            currency.setCurrencyName("Currency code cannot be empty.");
            return currency;
        }
        if (currencyCode.length() > 10) {
            currency.setCurrencyName("Currency code cannot exceed 10 characters.");
            return currency;
        }

        List<Currency> currencyList = getAllCurrency();
        List<String> currencyCodes = currencyList.stream()
                .map(Currency::getCurrencyCode)
                .collect(Collectors.toList());
        if (currencyCodes.contains(currencyCode)) {
            return currencyDao.getCurrencyByCode(currencyCode);
        }
        else {
            currency.setCurrencyName("Currency does not exists.");
            return currency;
        }
    }

    @Override
    public Currency addCurrency(Currency currency) {
        boolean isValid = validateCurrency(currency);

        if(!isValid) {
            return currency;
        } else {
            return currencyDao.addCurrency(currency);
        }
    }

    @Override
    public Currency updateCurrency(Currency currency) {
        String currencyCode = currency.getCurrencyCode();
        String currencyName = currency.getCurrencyName();

        if (currencyName.isEmpty()) {
            currency.setCurrencyName("Currency name cannot be empty.");
        } else if (currencyName.length() > 45) {
            currency.setCurrencyName("Currency name cannot exceed 45 characters.");
        } else {
            List<Currency> currencyList = getAllCurrency();
            List<String> currencyCodes = currencyList.stream()
                    .map(Currency::getCurrencyCode)
                    .collect(Collectors.toList());
            if (currencyCodes.contains(currencyCode)) {
                currencyDao.updateCurrency(currency);
            } else {
                currency.setCurrencyCode("Currency code does not exist.");
            }
        }
        return currency;
    }

    @Override
    public Currency deleteCurrencyByCode(String currencyCode) {
        Currency currency = new Currency();

        List<Currency> currencyList = getAllCurrency();
        List<String> currencyCodes = currencyList.stream()
                .map(Currency::getCurrencyCode)
                .collect(Collectors.toList());


        if (currencyCodes.contains(currencyCode)) {
            currency = getCurrencyByCode(currencyCode);
            currencyDao.deleteCurrencyByCode(currencyCode);
            return currency;
        } else {
            currency.setCurrencyCode("Currency code does not exists.");
        }
        return currency;
    }

    public boolean validateCurrency(Currency currency) {
        boolean isValid = true;

        String currencyCode = currency.getCurrencyCode();
        String currencyName = currency.getCurrencyName();

        if (currencyCode.isEmpty()) {
            currency.setCurrencyCode("Currency code cannot be empty.");
            isValid = false;
        }
        if (currencyCode.length() > 10) {
            currency.setCurrencyCode("Currency code cannot exceed 10 characters.");
            isValid = false;
        }

        if (currencyName.isEmpty()) {
            currency.setCurrencyName("Currency name cannot be empty.");
            isValid = false;
        }
        if (currencyName.length() > 45) {
            currency.setCurrencyName("Currency name cannot exceed 45 characters.");
            isValid = false;
        }

        List<Currency> currencyList = getAllCurrency();
        List<String> currencyCodes = currencyList.stream()
                .map(Currency::getCurrencyCode)
                .collect(Collectors.toList());
        if (currencyCodes.contains(currency.getCurrencyCode())) {
            currency.setCurrencyCode("Currency code already exists.");
            isValid = false;
        }

        return isValid;
    }
}
