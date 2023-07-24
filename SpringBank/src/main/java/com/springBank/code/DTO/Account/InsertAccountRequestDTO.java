package com.springBank.code.DTO.Account;

import com.springBank.code.Entity.Users;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

@AllArgsConstructor
@Getter
@Setter
public class InsertAccountRequestDTO implements Serializable {

    private String s_account_number;
    private String s_account_type;
    private String s_account_status;
    private Double f_account_balance;
    private String s_account_currency;
    private String s_account_creation_date;
    private Users owner;
    private List<Long> idTransactions;


    public List<Long> getIdTransactions() {
        return idTransactions;
    }
}