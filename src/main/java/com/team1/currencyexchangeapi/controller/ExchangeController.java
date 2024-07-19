package com.team1.currencyexchangeapi.controller;

import com.team1.currencyexchangeapi.model.Exchange;
import com.team1.currencyexchangeapi.service.ExchangeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/exchange")
@CrossOrigin
public class ExchangeController {

    @Autowired
    ExchangeService exchangeService;

    @PostMapping("/add")
    public Exchange addExchange(@RequestBody Exchange exchange) {

        return exchangeService.addExchange(exchange);

    }

    @GetMapping("/all")
    public List<Exchange> getAllExchange() {

        return exchangeService.getAllExchange();

    }

    @GetMapping("/username/{username}")
    public List<Exchange> getExchangeByUsername(@PathVariable String username) {

        return exchangeService.getExchangeByUsername(username);

    }

    @GetMapping("/{id}")
    public Exchange getExchangeById(@PathVariable int id) {

        return exchangeService.getExchangeById(id);

    }

    @PutMapping("/{id}")
    public Exchange updateExchange(@PathVariable int id, @RequestBody Exchange exchange) {
        exchange.setId(id);
        return exchangeService.updateExchange(exchange);
    }

    @DeleteMapping("/{id}")
    public Exchange deleteExchangeById(@PathVariable int id) {
        return exchangeService.deleteExchangeById(id);
    }
}
