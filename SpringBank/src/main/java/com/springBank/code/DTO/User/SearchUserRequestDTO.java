package com.springBank.code.DTO.User;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class SearchUserRequestDTO implements Serializable{

        private String s_name;
        private String user_id;
        private String s_surname;
        private String s_email;
        private String s_phone;
        private String s_tax_code;
}
