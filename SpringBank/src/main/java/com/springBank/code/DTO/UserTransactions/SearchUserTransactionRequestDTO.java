package com.springBank.code.DTO.UserTransactions;

import com.springBank.code.Entity.Account;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class SearchUserTransactionRequestDTO implements Serializable {

    private Long transaction_id;
    private String transaction_date;
    private String transaction_receiver;
    private String transaction_sender;

    private Account a_account;
}
