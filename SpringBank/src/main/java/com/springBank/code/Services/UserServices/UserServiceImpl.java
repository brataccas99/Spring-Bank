package com.springBank.code.Services.UserServices;

import com.springBank.code.DAO.AccountDAO;
import com.springBank.code.DAO.UserDAO;
import com.springBank.code.DTO.User.DeleteUserRequestDTO;
import com.springBank.code.DTO.User.InsertUserRequestDTO;
import com.springBank.code.DTO.User.UpdateUserRequestDTO;
import com.springBank.code.DTO.User.UserDTO;
import com.springBank.code.Entity.Account;
import com.springBank.code.Entity.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private UserDAO userDAO;
    @Autowired
    private AccountDAO accountDAO;
    @Override
    public Long insertUser(InsertUserRequestDTO request) {
        Users users = new Users();
        List<Account> accounts = new ArrayList<>();
        List<Long> idAccounts = request.getIdAccounts();
        if(!idAccounts.isEmpty()){
            for(Long id : idAccounts){
                Account account = new Account();
                account.setId(id);
                accounts.add(account);
            }
        }
        users.setAccounts(accounts);
        users.setNome(request.getS_name());
        users.setCognome(request.getS_surname());
        users.setEmail(request.getS_email());
        users.setTelefono(request.getS_phone());
        users.setTaxCode(request.getS_tax_code());
        return userDAO.save(users).getId();
    }

    @Override
    public Long updateUser(UpdateUserRequestDTO request) {
        Users users = new Users();
        users.setEmail(request.getS_email());
        users.setTelefono(request.getS_phone());
        return userDAO.save(users).getId();
    }

    @Override
    public void deleteUser(DeleteUserRequestDTO request) {
        Optional<Users> users = userDAO.findById(request.getN_id_user());
        if(users.isPresent()){
            userDAO.delete(users.get());
        }
    }

    @Override
    public Long searchUser(Long request) {
        UserDTO userDTO = new UserDTO();
        Optional<Users> users = userDAO.findById(request);

        if (users.isPresent()) {
            userDTO.setN_id_user(users.get().getId());
            userDTO.setS_name(users.get().getNome());
            userDTO.setS_surname(users.get().getCognome());
            userDTO.setS_email(users.get().getEmail());
            userDTO.setS_phone(users.get().getTelefono());
            userDTO.setS_tax_code(users.get().getTaxCode());
            List<Long> idAccounts = new ArrayList<>();
            for(Account account : users.get().getAccounts()){
                idAccounts.add(account.getId());
            }
            userDTO.setN_id_account(idAccounts);
        }
        return userDTO.getN_id_user();
    }

    @Override
    public String searchByNome(String request) {
        UserDTO userDTO = new UserDTO();
        Optional<Users> users = userDAO.findByNome(request);
        if (users.isPresent()) {
            userDTO.setN_id_user(users.get().getId());
            userDTO.setS_name(users.get().getNome());
            userDTO.setS_surname(users.get().getCognome());
            userDTO.setS_email(users.get().getEmail());
            userDTO.setS_phone(users.get().getTelefono());
            userDTO.setS_tax_code(users.get().getTaxCode());
            List<Long> idAccounts = new ArrayList<>();
            for(Account account : users.get().getAccounts()){
                idAccounts.add(account.getId());
            }
            userDTO.setN_id_account(idAccounts);
        }
        return userDTO.getS_name();
    }
}
