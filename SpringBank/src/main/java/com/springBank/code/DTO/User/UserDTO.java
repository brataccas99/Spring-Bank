package com.springBank.code.DTO.User;

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
public class UserDTO implements Serializable {

    private long n_id_user;
    private String s_name;
    private String s_surname;
    private String s_email;
    private String s_phone;
    private String s_tax_code;
    private List<Long> n_id_account;

}
