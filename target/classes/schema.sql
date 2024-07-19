-- Drop and recreate the database
-- DROP DATABASE IF EXISTS currenciesdb;
-- CREATE DATABASE currenciesdb;
-- USE currenciesdb;

-- Table structure for users
DROP TABLE IF EXISTS users;
CREATE TABLE users (
    username VARCHAR(10) NOT NULL,
    CONSTRAINT pk_users PRIMARY KEY (username)
);

-- Table structure for currencies
DROP TABLE IF EXISTS currencies;
CREATE TABLE currencies (
    currencyCode VARCHAR(10) NOT NULL,
    currencyName VARCHAR(45) NOT NULL,
    CONSTRAINT pk_currencies PRIMARY KEY (currencyCode)
);

-- Table structure for exchanges
DROP TABLE IF EXISTS exchanges;
CREATE TABLE exchanges (
    exchangeId INT AUTO_INCREMENT,
    username VARCHAR(10),
    exchangeDate DATE NOT NULL,
    exchangeRate DECIMAL(20,8) NOT NULL,
    baseAmount DECIMAL(10,2) NOT NULL,
    finalAmount DECIMAL(10,2) NOT NULL,
    CONSTRAINT pk_exchanges PRIMARY KEY (exchangeId),
    CONSTRAINT fk_exchanges_users FOREIGN KEY (username)
        REFERENCES users(username)
        ON UPDATE CASCADE
        ON DELETE CASCADE
);

-- Table structure for exchanges_currencies
DROP TABLE IF EXISTS exchanges_currencies;
CREATE TABLE exchanges_currencies (
    exchangeId INT NOT NULL,
    fromCurrencyCode VARCHAR(10) NOT NULL,
    toCurrencyCode VARCHAR(10) NOT NULL,
    CONSTRAINT pk_exchanges_currencies PRIMARY KEY (exchangeId, fromCurrencyCode, toCurrencyCode),
    CONSTRAINT fk_exchanges_currencies FOREIGN KEY (exchangeId)
        REFERENCES exchanges(exchangeId)
        ON UPDATE CASCADE
        ON DELETE CASCADE,
    CONSTRAINT fk_exchanges_currencies_fromCurrencies FOREIGN KEY (fromCurrencyCode)
        REFERENCES currencies(currencyCode)
        ON UPDATE CASCADE
        ON DELETE CASCADE,
    CONSTRAINT fk_exchanges_currencies_toCurrencies FOREIGN KEY (toCurrencyCode)
        REFERENCES currencies(currencyCode)
        ON UPDATE CASCADE
        ON DELETE CASCADE
);