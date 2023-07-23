package com.springBank.code.DTO.UserTransactions;

import com.springBank.code.Entity.Account;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@AllArgsConstructor
@Getter
@Setter
public class InsertUserTransactionRequestDTO implements Serializable {

    private String transaction_date;
    private String transaction_amount;
    private String transaction_description;
    private String transaction_result;
    private String transaction_receiver;
    private String transaction_sender;

    private Account a_account;
}
