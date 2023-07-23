package com.springBank.code.DTO.UserTransactions;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class DeleteUserTransactionRequestDTO implements Serializable {

    private Long n_id_user_transaction;

}
