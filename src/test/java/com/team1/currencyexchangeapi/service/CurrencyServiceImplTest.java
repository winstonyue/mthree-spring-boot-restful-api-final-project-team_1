package com.team1.currencyexchangeapi.service;

import com.team1.currencyexchangeapi.dao.CurrencyDao;
import com.team1.currencyexchangeapi.dao.CurrencyDaoStubImpl;
import com.team1.currencyexchangeapi.model.Currency;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CurrencyServiceImplTest {

    private CurrencyService currencyService;

    public CurrencyServiceImplTest() {
        CurrencyDao currencyDao = new CurrencyDaoStubImpl();
        currencyService = new CurrencyServiceImpl(currencyDao); // null for RestTemplate, assuming your service also handles external API calls
    }

    @Test
    @DisplayName("Get All Currencies")
    void testGetAllCurrencies() {
        List<Currency> currencies = currencyService.getAllCurrency();
        assertFalse(currencies.isEmpty());
        assertTrue(currencies.stream().anyMatch(currency -> "USD".equals(currency.getCurrencyCode())));
    }

    @Test
    @DisplayName("Get Currency By Code")
    void testGetCurrencyByCode() {
        Currency currency = currencyService.getCurrencyByCode("USD");
        assertNotNull(currency);
        assertEquals("USD", currency.getCurrencyCode());
    }

    @Test
    @DisplayName("Add New Currency")
    void testAddCurrency() {
        Currency newCurrency = new Currency();
        newCurrency.setCurrencyCode("GBP");
        newCurrency.setCurrencyName("British Pound");
        Currency addedCurrency = currencyService.addCurrency(newCurrency);
        assertNotNull(addedCurrency);
        assertEquals("GBP", addedCurrency.getCurrencyCode());
        // Verify it's actually added
        assertNotNull(currencyService.getCurrencyByCode("GBP"));
    }

    @Test
    @DisplayName("Update Currency")
    void testUpdateCurrency() {
        Currency currencyToUpdate = new Currency();
        currencyToUpdate.setCurrencyCode("USD");
        currencyToUpdate.setCurrencyName("US Dollar Updated");
        currencyService.updateCurrency(currencyToUpdate);
        Currency updatedCurrency = currencyService.getCurrencyByCode("USD");
        assertNotNull(updatedCurrency);
        assertEquals("US Dollar Updated", updatedCurrency.getCurrencyName());
    }

    @Test
    @DisplayName("Delete Currency By Code")
    void testDeleteCurrencyByCode() {
        currencyService.deleteCurrencyByCode("EUR");
        Currency deletedCurrency = currencyService.getCurrencyByCode("EUR");
        assertEquals("Currency does not exists.", deletedCurrency.getCurrencyName());
    }
}
