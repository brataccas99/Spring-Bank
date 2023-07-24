package com.springBank.code.DTO.CurrencyExchange;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CurrencyExchangeRequestDTO {
    private String sourceCurrency;
    private String targetCurrency;
    private Double amount;
}
