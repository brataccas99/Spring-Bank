package com.springBank.code.Services.AccountService;

import com.springBank.code.DTO.Account.DeleteAccountRequestDTO;
import com.springBank.code.DTO.Account.InsertAccountRequestDTO;
import com.springBank.code.DTO.Account.UpdateAccountRequestDTO;
import org.apache.catalina.User;
import org.springframework.stereotype.Service;

@Service
public interface AccountService {

        Long insertAccount(InsertAccountRequestDTO request);

        Long updateAccount(UpdateAccountRequestDTO request);

        void deleteAccount(DeleteAccountRequestDTO request);

        Long searchAccountById(Long id);

        String searchByOwner(User owner);
}
