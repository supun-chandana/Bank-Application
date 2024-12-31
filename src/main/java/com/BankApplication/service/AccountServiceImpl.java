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
    /*
    public Account createAccount(Account account) {
       Account account_saved = repo.save(account);
        return account_saved;
    }
    */

    public Account createAccount(Account account) {
        // Validate the account balance
        if (account.getAccount_balance() < 0) {
            throw new RuntimeException(".......Account cannot be created with a negative balance.......");
        }

        // Validate that the account name is not empty or null
        if (account.getAccount_holder_name() == null || account.getAccount_holder_name().trim().isEmpty()) {
            throw new RuntimeException(".......Account name cannot be empty.......");
        }



        // Save the account if validation passes
        Account account_saved = repo.save(account);
        return account_saved;
    }


    @Override
    public Account getAccountDetailsByAccountNumber(Long accountNumber) {
       Optional<Account> account= repo.findById(accountNumber);
       // throw exception if account is empty
       if(account.isEmpty()){
          throw new RuntimeException(".......Account does not exist ...!!!");



       }
       Account account_found = account.get();
       return account_found;
    }

    @Override
    /*
    public List<Account> getAllAccountDetails() {
        List<Account> listOfAccounts = repo.findAll();
        return listOfAccounts;
    }

     */
    public List<Account> getAllAccountDetails() {
        List<Account> listOfAccounts = repo.findAll();

        // Check if no accounts are found and throw an exception
        if (listOfAccounts.isEmpty()) {
            throw new RuntimeException(".......No accounts found in the database.......");
        }

        return listOfAccounts;
    }

    @Override
    /*
    public Account depositAmount(Long accountNumber, Double amount) {
        Optional <Account> account = repo.findById(accountNumber);
        if (account.isEmpty()){
            throw new RuntimeException(".......Account does not exist ...!!!");

        }
        Account accountPresent = account.get();
        Double totalBalance = accountPresent.getAccount_balance()+amount;
        accountPresent.setAccount_balance(totalBalance);
        repo.save(accountPresent);
        return accountPresent;
    }

     */

    public Account depositAmount(Long accountNumber, Double amount) {
        // Validate deposit amount
        if (amount <= 0) {
            throw new RuntimeException(".......Deposit amount must be greater than zero........");
        }

        // Find the account by account number
        Optional<Account> account = repo.findById(accountNumber);
        if (account.isEmpty()) {
            throw new RuntimeException(".......Account does not exist ...!!!");
        }

        // Update account balance
        Account accountPresent = account.get();
        Double totalBalance = accountPresent.getAccount_balance() + amount;
        accountPresent.setAccount_balance(totalBalance);
        repo.save(accountPresent);
        return accountPresent;
    }

    @Override

    public Account withdrawAmount(Long accountNumber, Double amount) {

        if (amount <= 0) {
            throw new RuntimeException("....Withdrawal amount must be greater than zero....");
        }

        Optional <Account> account = repo.findById(accountNumber);
        if (account.isEmpty()){
            throw new RuntimeException(".......Account does not exist ...!!!");
        }
        Account accountPresent = account.get();

        /*
        Double accountBalance = accountPresent.getAccount_balance()-amount;
        accountPresent.setAccount_balance(accountBalance);
        repo.save(accountPresent);
        return accountPresent;

         */

        Double currentBalance = accountPresent.getAccount_balance();

        // Check if withdrawal will result in a negative balance
        if (currentBalance < amount) {
            throw new RuntimeException("......Insufficient funds. Withdrawal not possible....!!!!");
        }

        Double updatedBalance = currentBalance - amount;
        accountPresent.setAccount_balance(updatedBalance);
        repo.save(accountPresent);

        return accountPresent;
    }

    @Override
    /*
    public Account closeAccount(Long accountNumber) {

        getAccountDetailsByAccountNumber(accountNumber);
        repo.deleteById(accountNumber);
        return null;
    }

     */
    public Account closeAccount(Long accountNumber) {
        // Find the account by account number
        Optional<Account> account = repo.findById(accountNumber);
        if (account.isEmpty()) {
            throw new RuntimeException("Account with account number " + accountNumber + " not found. Closure not possible.");
        }

        // Delete the account
        repo.deleteById(accountNumber);
        System.out.println("Account with account number " + accountNumber + " has been successfully closed.");

        return account.get();
    }
}
