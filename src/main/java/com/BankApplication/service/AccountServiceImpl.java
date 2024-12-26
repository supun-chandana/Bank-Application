package com.BankApplication.service;

import com.BankApplication.entity.Account;
import com.BankApplication.repo.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AccountServiceImpl implements AccountService {

    @Autowired
    AccountRepository repo; // access to repo

    //uses save method of JPA repo
    @Override
    public Account createAccount(Account account) {
       Account account_saved = repo.save(account);
        return account_saved;
    }

    @Override
    public Account getAccountDetailsByAccountNumber(Long accountNumber) {
       Optional<Account> account= repo.findById(accountNumber);
       // throw exception if account is empty
       if(account.isEmpty()){
           throw new RuntimeException("Account does not exist");
       }
       Account account_found = account.get();
       return account_found;
    }

    @Override
    public List<Account> getAllAccountDetails() {
        List<Account> listOfAccounts = repo.findAll();
        return listOfAccounts;
    }

    @Override
    public Account depositAmount(Long accountNumber, Double amount) {
        return null;
    }

    @Override
    public Account withdrawAmount(Long accountNumber, Double amount) {
        return null;
    }

    @Override
    public Account closeAccount(Long accountNumber) {
        return null;
    }
}
