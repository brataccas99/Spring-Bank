package com.springBank.code.DTO.User;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class UpdateUserRequestDTO implements Serializable {

        private String s_email;
        private String s_phone;
}
