package service;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import model.Account;

public class BankService {

    private List<Account> accounts;
    private Random random;
    private static final String DATA_FILE = "accounts.csv";

    public BankService() {
        accounts = new ArrayList<>();
        random = new Random();
        loadAccounts();
    }

    private void loadAccounts() {
        File file = new File(DATA_FILE);
        if (!file.exists()) {
            return;
        }
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                Account account = Account.fromCsvString(line);
                if (account != null) {
                    accounts.add(account);
                }
            }
        } catch (IOException e) {
            System.err.println("Error loading accounts from file: " + e.getMessage());
        }
    }

    private void saveAccounts() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(DATA_FILE))) {
            for (Account account : accounts) {
                writer.write(account.toCsvString());
                writer.newLine();
            }
        } catch (IOException e) {
            System.err.println("Error saving accounts to file: " + e.getMessage());
        }
    }

    // ✅ Unique account number generate karo
    private int generateAccountNumber() {
        // 100000–999999 ke beech random number
        int number;
        while (true) {
            number = 100000 + random.nextInt(900000);
            if (findAccountByNumber(number) == null) {
                break;
            }
        }
        return number;
    }

    // ✅ Account create karo
    public Account createAccount(String holderName, int pin, double initialDeposit) {
        int accNo = generateAccountNumber();
        if (initialDeposit < 0) {
            initialDeposit = 0;
        }
        Account account = new Account(accNo, holderName, pin, initialDeposit);
        accounts.add(account);
        saveAccounts(); // Save after creating a new account
        return account;
    }

    // ✅ Account search by account number
    public Account findAccountByNumber(int accountNumber) {
        for (Account acc : accounts) {
            if (acc.getAccountNumber() == accountNumber) {
                return acc;
            }
        }
        return null;
    }

    // ✅ Login check
    public Account login(int accountNumber, int pin) {
        Account acc = findAccountByNumber(accountNumber);
        if (acc != null && acc.validatePin(pin)) {
            return acc;
        }
        return null;
    }
}
