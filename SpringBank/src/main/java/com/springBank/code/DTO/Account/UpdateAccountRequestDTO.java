package com.springBank.code.DTO.Account;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class UpdateAccountRequestDTO implements Serializable{

        private String s_account_status;
        private String s_account_currency;
}
