package com.springBank.code.DTO.Account;

import com.springBank.code.Entity.Users;
import com.springBank.code.Entity.UserTransactions;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class AccountDTO implements Serializable {

    private Long n_id_account;
    private String s_account_number;
    private String s_account_type;
    private String s_account_status;
    private Double f_account_balance;
    private String s_account_currency;
    private String s_account_creation_date;

    private Users o_owner;

    private List<Long> t_transactions;


}
