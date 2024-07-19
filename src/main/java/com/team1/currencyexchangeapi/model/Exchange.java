package com.team1.currencyexchangeapi.model;

import org.apache.tomcat.jni.Local;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;

public class Exchange {
    private int id;
    private String username;
    private String fromCurrencyCode;
    private String toCurrencyCode;
    private LocalDate date;
    private BigDecimal exchangeRate;
    private BigDecimal baseAmount;
    private BigDecimal finalAmount;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getFromCurrencyCode() {
        return fromCurrencyCode;
    }

    public void setFromCurrencyCode(String fromCurrencyCode) {
        this.fromCurrencyCode = fromCurrencyCode;
    }

    public String getToCurrencyCode() {
        return toCurrencyCode;
    }

    public void setToCurrencyCode(String toCurrencyCode) {
        this.toCurrencyCode = toCurrencyCode;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public BigDecimal getExchangeRate() {
        return exchangeRate;
    }

    public void setExchangeRate(BigDecimal exchangeRate) {
        this.exchangeRate = exchangeRate;
    }

    public BigDecimal getBaseAmount() {
        return baseAmount;
    }

    public void setBaseAmount(BigDecimal baseAmount) {
        this.baseAmount = baseAmount;
    }

    public BigDecimal getFinalAmount() {
        return finalAmount;
    }

    public void setFinalAmount(BigDecimal finalAmount) {
        this.finalAmount = finalAmount;
    }

    public void calculateFinalAmount() {
        finalAmount = exchangeRate.multiply(baseAmount).setScale(2, RoundingMode.HALF_UP);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null ||
            this.getClass() != obj.getClass()) {
            return false;
        }

        Exchange exchange = (Exchange) obj;

        return (this.id == exchange.id &&
                this.username.equals(exchange.username) &&
                this.fromCurrencyCode.equalsIgnoreCase(exchange.fromCurrencyCode) &&
                this.toCurrencyCode.equalsIgnoreCase(exchange.toCurrencyCode) &&
                this.date.equals(exchange.date) &&
                this.exchangeRate.compareTo(exchange.exchangeRate) == 0 &&
                this.baseAmount.compareTo(exchange.baseAmount) == 0 &&
                this.finalAmount.compareTo(exchange.finalAmount) == 0);
    }

}
