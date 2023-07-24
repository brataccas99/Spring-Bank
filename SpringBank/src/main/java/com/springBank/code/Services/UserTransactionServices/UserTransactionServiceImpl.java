package com.springBank.code.Services.UserTransactionServices;

import com.springBank.code.DAO.AccountDAO;
import com.springBank.code.DAO.UserTransactionDAO;
import com.springBank.code.DTO.UserTransactions.DeleteUserTransactionRequestDTO;
import com.springBank.code.DTO.UserTransactions.InsertUserTransactionRequestDTO;
import com.springBank.code.DTO.UserTransactions.UserTransactionsDTO;
import com.springBank.code.Entity.Account;
import com.springBank.code.Entity.UserTransactions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserTransactionServiceImpl implements UserTransactionService {

    @Autowired
    private UserTransactionDAO userTransactionDAO;
    @Autowired
    private AccountDAO accountDAO;

    @Override
    public Long insertUserTransaction(InsertUserTransactionRequestDTO request) {
        UserTransactions userTransactions = new UserTransactions();

        userTransactions.setDate(request.getTransaction_date());
        userTransactions.setAmount(request.getTransaction_amount());
        userTransactions.setDescription(request.getTransaction_description());
        userTransactions.setResult(request.getTransaction_result());
        userTransactions.setReceiver(request.getTransaction_receiver());
        userTransactions.setSender(request.getTransaction_sender());
        userTransactions.setAccount(accountDAO.findById(request.getA_account().getId()).get());

        return userTransactionDAO.save(userTransactions).getId();
    }

    @Override
    public void deleteUserTransaction(DeleteUserTransactionRequestDTO request) {
        Optional<UserTransactions> userTransactions = userTransactionDAO.findById(request.getN_id_user_transaction());
        if (userTransactions.isPresent()) {
            userTransactionDAO.delete(userTransactions.get());
        }
    }

    @Override
    public Long searchUserTransactionById(Long id) {
        UserTransactionsDTO userTransactionsDTO = new UserTransactionsDTO();
        Optional<UserTransactions> userTransactions = userTransactionDAO.findById(id);
        if (userTransactions.isPresent()) {
            userTransactionsDTO.setTransaction_id(userTransactions.get().getId());
            userTransactionsDTO.setTransaction_date(userTransactions.get().getDate());
            userTransactionsDTO.setTransaction_amount(userTransactions.get().getAmount());
            userTransactionsDTO.setTransaction_description(userTransactions.get().getDescription());
            userTransactionsDTO.setTransaction_result(userTransactions.get().getResult());
            userTransactionsDTO.setTransaction_receiver(userTransactions.get().getReceiver());
            userTransactionsDTO.setTransaction_sender(userTransactions.get().getSender());
            userTransactionsDTO.setA_account(userTransactions.get().getAccount());
        }
        return userTransactionsDTO.getTransaction_id();
    }

    @Override
    public String searchByDate(String date) {
        UserTransactionsDTO userTransactionsDTO = new UserTransactionsDTO();
        Optional<UserTransactions> userTransactions = userTransactionDAO.findByDate(date);
        if (userTransactions.isPresent()) {
            userTransactionsDTO.setTransaction_id(userTransactions.get().getId());
            userTransactionsDTO.setTransaction_date(userTransactions.get().getDate());
            userTransactionsDTO.setTransaction_amount(userTransactions.get().getAmount());
            userTransactionsDTO.setTransaction_description(userTransactions.get().getDescription());
            userTransactionsDTO.setTransaction_result(userTransactions.get().getResult());
            userTransactionsDTO.setTransaction_receiver(userTransactions.get().getReceiver());
            userTransactionsDTO.setTransaction_sender(userTransactions.get().getSender());
            userTransactionsDTO.setA_account(userTransactions.get().getAccount());
        }
        return userTransactions.get().getDate();
    }
}
