package com.springBank.code.Services.CurrencyServices;

public interface CurrencyService {
    Double exchangeCurrency(String sourceCurrency, String targetCurrency, Double amount);
}
