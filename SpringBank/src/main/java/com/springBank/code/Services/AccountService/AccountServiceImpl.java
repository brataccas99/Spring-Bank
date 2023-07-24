package com.springBank.code.Services.AccountService;

import com.springBank.code.DAO.AccountDAO;
import com.springBank.code.DAO.UserDAO;
import com.springBank.code.DAO.UserTransactionDAO;
import com.springBank.code.DTO.Account.AccountDTO;
import com.springBank.code.DTO.Account.DeleteAccountRequestDTO;
import com.springBank.code.DTO.Account.InsertAccountRequestDTO;
import com.springBank.code.DTO.Account.UpdateAccountRequestDTO;
import com.springBank.code.Entity.Account;
import com.springBank.code.Entity.UserTransactions;
import com.springBank.code.Entity.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class AccountServiceImpl implements AccountService{

    @Autowired
    private AccountDAO accountDAO;
    @Autowired
    private UserDAO userDAO;
    @Autowired
    private UserTransactionDAO userTransactionDAO;
    @Override
    public Long insertAccount(InsertAccountRequestDTO request) {
        Account account = new Account();
        List<UserTransactions> transactions = new ArrayList<>();
        List<Long> idTransactions = request.getIdTransactions();
        if(!idTransactions.isEmpty()){
            for(Long id : idTransactions){
                UserTransactions transaction = new UserTransactions();
                transaction.setId(id);
                transactions.add(transaction);
            }
        }
        account.setIban(request.getS_account_number());
        account.setType(request.getS_account_type());
        account.setStatus(request.getS_account_status());
        account.setBalance(String.valueOf(request.getF_account_balance()));
        account.setCreationDate(request.getS_account_creation_date());
        account.setOwner(request.getOwner());
        account.setTransactions(transactions);

        return accountDAO.save(account).getId();
    }

    @Override
    public Long updateAccount(UpdateAccountRequestDTO request) {
        Account account = new Account();
        account.setStatus(request.getS_account_status());
        return accountDAO.save(account).getId();
    }

    @Override
    public void deleteAccount(DeleteAccountRequestDTO request) {
        Optional<Account> account = accountDAO.findById(request.getN_id_account());
        if(account.isPresent()){
            accountDAO.delete(account.get());
        }
    }

    @Override
    public Long searchAccountById(Long id) {
        AccountDTO accountDTO = new AccountDTO();
        Optional<Account> account = accountDAO.findById(id);
        if (account.isPresent()) {
            accountDTO.setS_account_number(account.get().getIban());
            accountDTO.setS_account_type(account.get().getType());
            accountDTO.setS_account_status(account.get().getStatus());
            accountDTO.setF_account_balance(Double.valueOf(account.get().getBalance()));
            accountDTO.setS_account_creation_date(account.get().getCreationDate());
            accountDTO.setO_owner(account.get().getOwner());
            List<Long> idTransactions = new ArrayList<>();
            for (UserTransactions transaction : account.get().getTransactions()) {
                idTransactions.add(transaction.getId());
            }
            accountDTO.setT_transactions(idTransactions);
        }
            return accountDTO.getN_id_account();

    }
    @Override
    public String searchByOwner(Users owner) {
          AccountDTO accountDTO = new AccountDTO();
            Optional<Account> account = accountDAO.findByOwner(owner);
            if (account.isPresent()) {
                accountDTO.setS_account_number(account.get().getIban());
                accountDTO.setS_account_type(account.get().getType());
                accountDTO.setS_account_status(account.get().getStatus());
                accountDTO.setF_account_balance(Double.valueOf(account.get().getBalance()));
                accountDTO.setS_account_creation_date(account.get().getCreationDate());
                accountDTO.setO_owner(account.get().getOwner());
                List<Long> idTransactions = new ArrayList<>();
                for (UserTransactions transaction : account.get().getTransactions()) {
                    idTransactions.add(transaction.getId());
                }
                accountDTO.setT_transactions(idTransactions);
            }
                return accountDTO.getO_owner().toString();

    }
}
