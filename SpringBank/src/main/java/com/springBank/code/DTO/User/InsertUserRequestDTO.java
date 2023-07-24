package com.springBank.code.DTO.User;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

@AllArgsConstructor
@Getter
@Setter
public class InsertUserRequestDTO implements Serializable {

    private String s_name;
    private String s_surname;
    private String s_email;
    private String s_phone;
    private String s_tax_code;
    private List<Long> idAccounts;


    public List<Long> getIdAccounts() {
        return idAccounts;
    }
}
