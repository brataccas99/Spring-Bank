package com.springBank.code.DTO.Account;

import com.springBank.code.Entity.Users;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class SearchAccountRequestDTO implements Serializable {

    private String s_account_number;
    private String s_account_creation_date;
    private String account_id;
    private Users o_owner;

}
