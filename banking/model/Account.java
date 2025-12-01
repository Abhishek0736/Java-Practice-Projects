package model;

public class Account {
    // ✅ Private fields (Encapsulation)
    private int accountNumber;
    private String holderName;
    private int pin;
    private double balance;

    // ✅ Constructor
    public Account(int accountNumber, String holderName, int pin, double balance) {
        this.accountNumber = accountNumber;
        this.holderName = holderName;
        this.pin = pin;
        this.balance = balance;
    }

    // ✅ Getters (sirf read)
    public int getAccountNumber() {
        return accountNumber;
    }

    public String getHolderName() {
        return holderName;
    }

    public double getBalance() {
        return balance;
    }

    // ✅ PIN check method
    public boolean validatePin(int inputPin) {
        return this.pin == inputPin;
    }

    // ✅ Deposit method
    public void deposit(double amount) {
        if (amount > 0) {
            balance += amount;
        }
    }

    // ✅ Withdraw method (with check)
    public boolean withdraw(double amount) {
        if (amount > 0 && amount <= balance) {
            balance -= amount;
            return true;
        }
        return false;
    }

    // ✅ Convert Account to CSV format
    public String toCsvString() {
        return accountNumber + "," + holderName + "," + pin + "," + balance;
    }

    // ✅ Create Account from CSV format
    public static Account fromCsvString(String csv) {
        String[] parts = csv.split(",");
        if (parts.length != 4) {
            return null;
        }
        try {
            int accountNumber = Integer.parseInt(parts[0]);
            String holderName = parts[1];
            int pin = Integer.parseInt(parts[2]);
            double balance = Double.parseDouble(parts[3]);
            return new Account(accountNumber, holderName, pin, balance);
        } catch (NumberFormatException e) {
            return null;
        }
    }
}
