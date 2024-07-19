package com.team1.currencyexchangeapi.dao;

import com.team1.currencyexchangeapi.dao.mappers.ExchangeMapper;
import com.team1.currencyexchangeapi.model.Exchange;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.List;

@Repository
public class ExchangeDaoDBImpl implements ExchangeDao{

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public ExchangeDaoDBImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Exchange addExchange(Exchange exchange) {
        final String insertExchangeSql =
                "INSERT INTO exchanges (username, exchangeDate, exchangeRate, " +
                        "baseAmount, finalAmount) " +
                "VALUES (?,?,?,?,?);";
        GeneratedKeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update((Connection conn) -> {

            PreparedStatement statement = conn.prepareStatement(
                    insertExchangeSql,
                    Statement.RETURN_GENERATED_KEYS);

            statement.setString(1, exchange.getUsername());
            statement.setDate(2, Date.valueOf(exchange.getDate()));
            statement.setBigDecimal(3, exchange.getExchangeRate());
            statement.setBigDecimal(4, exchange.getBaseAmount());
            statement.setBigDecimal(5, exchange.getFinalAmount());
            return statement;

        }, keyHolder);

        int exchangeId = keyHolder.getKey().intValue();
        exchange.setId(exchangeId);

        final String insertExchangeCurrencySql =
                "INSERT INTO exchanges_currencies (exchangeId, fromCurrencyCode, " +
                        "toCurrencyCode) " +
                "VALUES (?,?,?);";

        jdbcTemplate.update(
                insertExchangeCurrencySql,
                exchangeId,
                exchange.getFromCurrencyCode(),
                exchange.getToCurrencyCode()
        );

        return exchange;
    }

    @Override
    public List<Exchange> getAllExchange() {
        final String sql =
                "SELECT e.exchangeId, e.username, ec.fromCurrencyCode, " +
                        "ec.toCurrencyCode, e.exchangeDate, e.exchangeRate, " +
                        "e.baseAmount, e.finalAmount " +
                "FROM exchanges e " +
                "INNER JOIN exchanges_currencies ec ON e.exchangeId = ec.exchangeId;";

        return jdbcTemplate.query(sql, new ExchangeMapper());
    }

    @Override
    public List<Exchange> getExchangeByUsername(String username) {
        final String sql =
                "SELECT e.exchangeId, e.username, ec.fromCurrencyCode, " +
                        "ec.toCurrencyCode, e.exchangeDate, e.exchangeRate, " +
                        "e.baseAmount, e.finalAmount " +
                "FROM exchanges e " +
                "INNER JOIN exchanges_currencies ec ON e.exchangeId = ec.exchangeId " +
                "WHERE e.username = ?;";

        return jdbcTemplate.query(sql, new ExchangeMapper(), username);
    }

    @Override
    public Exchange getExchangeById(int id) {
        final String sql =
                "SELECT e.exchangeId, e.username, ec.fromCurrencyCode, " +
                        "ec.toCurrencyCode, e.exchangeDate, e.exchangeRate, " +
                        "e.baseAmount, e.finalAmount " +
                        "FROM exchanges e " +
                        "INNER JOIN exchanges_currencies ec ON e.exchangeId = ec.exchangeId " +
                        "WHERE e.exchangeId = ?;";

        return jdbcTemplate.queryForObject(sql, new ExchangeMapper(), id);
    }

    @Override
    public void updateExchange(Exchange exchange) {
        final String exchangeSql =
                "UPDATE exchanges SET " +
                "exchangeDate = ?, " +
                "exchangeRate = ?, " +
                "baseAmount = ?, " +
                "finalAmount = ? " +
                "WHERE exchangeId = ?;";
        jdbcTemplate.update(
                exchangeSql,
                exchange.getDate(),
                exchange.getExchangeRate(),
                exchange.getBaseAmount(),
                exchange.getFinalAmount(),
                exchange.getId()
        );

        final String exchangeCurrencySql =
                "UPDATE exchanges_currencies SET " +
                "fromCurrencyCode = ?, " +
                "toCurrencyCode = ? " +
                "WHERE exchangeId = ?;";
        jdbcTemplate.update(
                exchangeCurrencySql,
                exchange.getFromCurrencyCode(),
                exchange.getToCurrencyCode(),
                exchange.getId()
        );
    }

    @Override
    public void deleteExchangeById(int id) {
        final String sql =
                "DELETE FROM exchanges " +
                "WHERE exchangeId = ?;";

        jdbcTemplate.update(sql, id);
    }
}
