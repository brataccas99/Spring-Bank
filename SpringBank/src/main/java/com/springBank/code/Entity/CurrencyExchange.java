package com.springBank.code.Entity;


import jakarta.persistence.Entity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(name = "currency_exchange")
public class CurrencyExchange implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "exchange_id")
    private Long id;

    @Column(name = "source_currency")
    private String sourceCurrency;

    @Column(name = "target_currency")
    private String targetCurrency;

    @Column(name = "exchange_rate")
    private Double exchangeRate;

    @Column(name = "amount")
    private Double amount;

    @Column(name = "exchanged_amount")
    private Double exchangedAmount;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "exchange_date")
    private Date exchangeDate;
}