package com.springBank.code.DTO.Account;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class DeleteAccountRequestDTO implements Serializable{

    private Long n_id_account;

}
