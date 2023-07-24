package com.springBank.code.Services.UserTransactionServices;

import com.springBank.code.DTO.UserTransactions.DeleteUserTransactionRequestDTO;
import com.springBank.code.DTO.UserTransactions.InsertUserTransactionRequestDTO;
import org.springframework.stereotype.Service;

@Service
public interface UserTransactionService {

    Long insertUserTransaction(InsertUserTransactionRequestDTO request);

   void deleteUserTransaction(DeleteUserTransactionRequestDTO request);

    Long searchUserTransactionById(Long id);

    String searchByDate(String date);

}
