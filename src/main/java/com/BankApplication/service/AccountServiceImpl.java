package com.BankApplication.service;

import com.BankApplication.entity.Account;
import com.BankApplication.repo.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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
        return null;
    }

    @Override
    public List<Account> getAllAccountDetails() {
        return List.of();
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
