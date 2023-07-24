package com.springBank.code.Services.UserServices;

import com.springBank.code.DTO.User.DeleteUserRequestDTO;
import com.springBank.code.DTO.User.InsertUserRequestDTO;
import com.springBank.code.DTO.User.UpdateUserRequestDTO;
import org.springframework.stereotype.Service;

@Service
public interface UserService {

    Long insertUser(InsertUserRequestDTO request);

    Long updateUser(UpdateUserRequestDTO request);

    void deleteUser(DeleteUserRequestDTO request);

    Long searchUser(Long request);

    String searchByNome(String request);
}
