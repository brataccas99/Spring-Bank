package com.springBank.code.DAO;


import com.springBank.code.Entity.CurrencyExchange;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CurrencyDAO extends JpaRepository<CurrencyExchange, Long> {

    @Query("SELECT ce FROM CurrencyExchange ce WHERE ce.sourceCurrency = :sourceCurrency AND ce.targetCurrency = :targetCurrency")
    CurrencyExchange exchangeCurrency(@Param("sourceCurrency") String sourceCurrency, @Param("targetCurrency") String targetCurrency);

    default Double getExchangeRate(String sourceCurrency, String targetCurrency) {
        if (sourceCurrency.equals("USD") && targetCurrency.equals("EUR")) {
            return 0.85; // Assuming 1 USD = 0.85 EUR
        } else if (sourceCurrency.equals("EUR") && targetCurrency.equals("USD")) {
            return 1.18; // Assuming 1 EUR = 1.18 USD
        } else {
            return 1.0; // Default exchange rate (1 unit of sourceCurrency = 1 unit of targetCurrency)
        }
    }
}
