package com.springBank.code.DTO.User;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class DeleteUserRequestDTO implements Serializable {

    private Long n_id_user;

}
