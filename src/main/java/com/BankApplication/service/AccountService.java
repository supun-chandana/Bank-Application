package com.BankApplication.service;

import com.BankApplication.entity.Account;

import java.util.List;

public interface AccountService {

    //crate account method
    public Account createAccount (Account account);

    //take account details based on ACC No
    public Account getAccountDetailsByAccountNumber (Long accountNumber);

    //return all acc details
    public List<Account> getAllAccountDetails();

    //deposit to Acc
    public Account depositAmount (Long accountNumber, Double amount);

    //withdraw
    public Account withdrawAmount (Long accountNumber, Double amount);

    //close Acc
    public Account closeAccount (Long accountNumber);


}
