package com.springBank.code.DAO;


import com.springBank.code.DTO.CurrencyExchange.CurrencyExchangeRequestDTO;
import com.springBank.code.DTO.CurrencyExchange.CurrencyExchangeResponseDTO;
import com.springBank.code.Entity.CurrencyExchange;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CurrencyDAO extends JpaRepository<CurrencyExchange, Long> {
    CurrencyExchangeResponseDTO exchangeCurrency(CurrencyExchangeRequestDTO requestDTO);

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
