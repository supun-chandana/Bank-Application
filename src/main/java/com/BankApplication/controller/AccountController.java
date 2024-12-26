package com.BankApplication.controller;

import com.BankApplication.entity.Account;
import com.BankApplication.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/account")  //request mapping url

public class AccountController {


    //use service layer to create account
    @Autowired
    AccountService service;

    //Creating the account
    @PostMapping("/create")
    public ResponseEntity<Account>  createAccount(@RequestBody Account account){

       Account createAccount = service.createAccount(account);

        return ResponseEntity.status(HttpStatus.CREATED).body(createAccount);
    }


    //Account Details by Acc No
    @GetMapping("/{accountNumber}")
    public Account getAccountByAccountNumber(@PathVariable Long accountNumber){
        Account account = service.getAccountDetailsByAccountNumber(accountNumber);
        return account;
    }


    //get all account details
    @GetMapping("/getallaccounts")
    public List<Account> getAllAccountDetails(){
        List<Account> allAccountDetails = service.getAllAccountDetails();
        return allAccountDetails;
    }

}
