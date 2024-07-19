package com.team1.currencyexchangeapi.controller;

import com.team1.currencyexchangeapi.model.Currency;
import com.team1.currencyexchangeapi.service.CurrencyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/currency")
public class CurrencyController {

    private final CurrencyService currencyService;

    @Autowired
    public CurrencyController(CurrencyService currencyService) {
        this.currencyService = currencyService;
    }

    @GetMapping("/all")
    public ResponseEntity<List<Currency>> getAllCurrencies() {
        List<Currency> currencies = currencyService.getAllCurrency();
        return new ResponseEntity<>(currencies, HttpStatus.OK);
    }

    @GetMapping("/{currencyCode}")
    public Currency getCurrencyByCode(@PathVariable String currencyCode) {
        return currencyService.getCurrencyByCode(currencyCode);
    }

    @PostMapping
    public Currency addCurrency(@RequestBody Currency currency) {
        return currencyService.addCurrency(currency);
    }

    @PutMapping("/{currencyCode}")
    public Currency updateCurrency(@PathVariable String currencyCode, @RequestBody Currency currency) {
        currency.setCurrencyCode(currencyCode);
        return currencyService.updateCurrency(currency);
    }

    @DeleteMapping("/{currencyCode}")
    public Currency deleteCurrencyByCode(@PathVariable String currencyCode) {
        return currencyService.deleteCurrencyByCode(currencyCode);
    }

//    @GetMapping("/exchange")
//    public ResponseEntity<BigDecimal> getExchangeRate(@RequestParam String from, @RequestParam String to, @RequestParam(required = false) String date) {
//        if (date == null || date.isEmpty()) {
//            date = "latest"; // Use 'latest' if no date provided
//        }
//        BigDecimal exchangeRate = currencyService.getExchangeRate(from, to, date);
//        if (exchangeRate != null) {
//            return new ResponseEntity<>(exchangeRate, HttpStatus.OK);
//        } else {
//            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//        }
//    }
}
