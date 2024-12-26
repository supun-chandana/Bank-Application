package com.BankApplication.ui;

import com.BankApplication.entity.Account;
import com.BankApplication.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Scanner;

@Component
public class MenuApplication {

    @Autowired
    private AccountService accountService;

    public void start() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("\n=== Bank Application Menu ===");
            System.out.println("1. Create Account");
            System.out.println("2. Get Account Details by Account Number");
            System.out.println("3. Get All Account Details");
            System.out.println("4. Deposit Money");
            System.out.println("5. Withdraw Money");
            System.out.println("6. Close Account");
            System.out.println("7. Exit");
            System.out.print("Enter your choice: ");

            int choice = scanner.nextInt();
            switch (choice) {
                case 1:
                    createAccount(scanner);
                    break;
                case 2:
                    getAccountDetails(scanner);
                    break;
                case 3:
                    getAllAccounts();
                    break;
                case 4:
                    depositMoney(scanner);
                    break;
                case 5:
                    withdrawMoney(scanner);
                    break;
                case 6:
                    closeAccount(scanner);
                    break;
                case 7:
                    System.out.println("Exiting the application.");
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private void createAccount(Scanner scanner) {
        System.out.print("Enter account holder name: ");
        scanner.nextLine(); // Consume newline
        String name = scanner.nextLine();
        System.out.print("Enter initial balance: ");
        double balance = scanner.nextDouble();

        Account account = new Account(name, balance);
        Account createdAccount = accountService.createAccount(account);
        System.out.println("Account created successfully: " + createdAccount);
    }

    private void getAccountDetails(Scanner scanner) {
        System.out.print("Enter account number: ");
        Long accountNumber = scanner.nextLong();

        Account account = accountService.getAccountDetailsByAccountNumber(accountNumber);
        if (account != null) {
            System.out.println("Account Details: " + account);
        } else {
            System.out.println("Account not found.");
        }
    }

    private void getAllAccounts() {
        System.out.println("Fetching all account details...");
        accountService.getAllAccountDetails().forEach(System.out::println);
    }

    private void depositMoney(Scanner scanner) {
        System.out.print("Enter account number: ");
        Long accountNumber = scanner.nextLong();
        System.out.print("Enter amount to deposit: ");
        double amount = scanner.nextDouble();

        Account updatedAccount = accountService.depositAmount(accountNumber, amount);
        if (updatedAccount != null) {
            System.out.println("Deposit successful. Updated Account Details: " + updatedAccount);
        } else {
            System.out.println("Account not found.");
        }
    }

    private void withdrawMoney(Scanner scanner) {
        System.out.print("Enter account number: ");
        Long accountNumber = scanner.nextLong();
        System.out.print("Enter amount to withdraw: ");
        double amount = scanner.nextDouble();

        Account updatedAccount = accountService.withdrawAmount(accountNumber, amount);
        if (updatedAccount != null) {
            System.out.println("Withdrawal successful. Updated Account Details: " + updatedAccount);
        } else {
            System.out.println("Account not found or insufficient balance.");
        }
    }

    private void closeAccount(Scanner scanner) {
        System.out.print("Enter account number: ");
        Long accountNumber = scanner.nextLong();

        Account closedAccount = accountService.closeAccount(accountNumber);
        if (closedAccount != null) {
            System.out.println("Account closed successfully: " + closedAccount);
        } else {
            System.out.println("Account not found.");
        }
    }
}