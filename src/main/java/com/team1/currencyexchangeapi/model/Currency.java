package com.team1.currencyexchangeapi.model;

public class Currency {

    private String currencyCode;
    private String currencyName;

    public String getCurrencyCode() {
        return currencyCode;
    }

    public void setCurrencyCode(String currencyCode) {
        this.currencyCode = currencyCode;
    }

    public String getCurrencyName() {
        return currencyName;
    }

    public void setCurrencyName(String currencyName) {
        this.currencyName = currencyName;
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

        Currency currency = (Currency) obj;

        return (this.currencyCode.equalsIgnoreCase(currency.currencyCode) &&
                this.currencyName.equalsIgnoreCase(currency.currencyName));
    }
}

