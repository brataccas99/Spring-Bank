package com.springBank.code.DTO.UserTransactions;

import com.springBank.code.Entity.Account;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UserTransactionsDTO implements Serializable {

    private Long transaction_id;
    private String transaction_date;
    private String transaction_amount;
    private String transaction_description;
    private String transaction_result;
    private String transaction_receiver;
    private String transaction_sender;

    private Account a_account;

}
