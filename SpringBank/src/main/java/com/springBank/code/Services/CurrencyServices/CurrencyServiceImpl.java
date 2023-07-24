package com.springBank.code.Services.CurrencyServices;

import com.springBank.code.DAO.CurrencyDAO;
import com.springBank.code.DTO.CurrencyExchange.CurrencyExchangeRequestDTO;
import com.springBank.code.DTO.CurrencyExchange.CurrencyExchangeResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

    @Service
    public class CurrencyServiceImpl implements CurrencyService{

        @Autowired
        private CurrencyDAO currencyDAO;

        public Double exchangeCurrency(String sourceCurrency, String targetCurrency, Double amount) {
            CurrencyExchangeRequestDTO requestDTO = new CurrencyExchangeRequestDTO();
            requestDTO.setSourceCurrency(sourceCurrency);
            requestDTO.setTargetCurrency(targetCurrency);
            requestDTO.setAmount(amount);

            // Get the exchange rate from the CurrencyDAO (use the getExchangeRate method)
            Double exchangeRate = currencyDAO.getExchangeRate(sourceCurrency, targetCurrency);

            // Perform the currency exchange calculation based on the obtained exchange rate
            Double exchangedAmount = amount * exchangeRate;

            return exchangedAmount;
        }
    }

