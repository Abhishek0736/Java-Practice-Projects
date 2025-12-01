import java.util.Scanner;

import model.Account;
import service.BankService;

public class Main {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        BankService bankService = new BankService();

        while (true) {
            System.out.println("\n===== Welcome to Java Bank =====");
            System.out.println("1. Create Account");
            System.out.println("2. Login");
            System.out.println("3. Exit");
            System.out.print("Choose option: ");

            int choice = sc.nextInt();
            sc.nextLine(); // buffer clear

            switch (choice) {
                case 1:
                    createAccountMenu(sc, bankService);
                    break;
                case 2:
                    loginMenu(sc, bankService);
                    break;
                case 3:
                    System.out.println("Thank you for using Java Bank. Goodbye!");
                    sc.close();
                    return;
                default:
                    System.out.println("Invalid choice, please try again.");
            }
        }
    }

    private static void createAccountMenu(Scanner sc, BankService bankService) {
        System.out.print("Enter your name: ");
        String name = sc.nextLine();

        int pin;
        while (true) {
            System.out.print("Set 4-digit PIN: ");
            pin = sc.nextInt();
            if (pin >= 1000 && pin <= 9999) {
                break;
            } else {
                System.out.println("Invalid PIN. Please enter a 4-digit number.");
            }
        }

        System.out.print("Initial deposit amount: ");
        double initialAmount = sc.nextDouble();

        Account newAcc = bankService.createAccount(name, pin, initialAmount);
        System.out.println("\n Account created successfully!");
        System.out.println("Your Account Number: " + newAcc.getAccountNumber());
        System.out.println("Please remember it for login.");
    }

    private static void loginMenu(Scanner sc, BankService bankService) {
        System.out.print("Enter Account Number: ");
        int accNo = sc.nextInt();

        System.out.print("Enter PIN: ");
        int pin = sc.nextInt();

        Account loggedIn = bankService.login(accNo, pin);

        if (loggedIn == null) {
            System.out.println("Invalid account number or PIN.");
            return;
        }

        System.out.println("\n Login successful! Welcome, " + loggedIn.getHolderName() + "!");
        accountOperations(sc, loggedIn);
    }

    private static void accountOperations(Scanner sc, Account account) {
        while (true) {
            System.out.println("\n===== Account Menu =====");
            System.out.println("1. Check Balance");
            System.out.println("2. Deposit");
            System.out.println("3. Withdraw");
            System.out.println("4. Logout");
            System.out.print("Choose option: ");

            int option = sc.nextInt();

            switch (option) {
                case 1:
                    System.out.println("Your current balance: " + account.getBalance());
                    break;
                case 2:
                    System.out.print("Enter amount to deposit: ");
                    double dep = sc.nextDouble();
                    account.deposit(dep);
                    System.out.println(" Amount deposited. New balance: " + account.getBalance());
                    break;
                case 3:
                    System.out.print("Enter amount to withdraw: ");
                    double w = sc.nextDouble();
                    boolean success = account.withdraw(w);
                    if (success) {
                        System.out.println(" Please collect cash. New balance: " + account.getBalance());
                    } else {
                        System.out.println(" Insufficient balance or invalid amount.");
                    }
                    break;
                case 4:
                    System.out.println("Logging out...");
                    return;
                default:
                    System.out.println("Invalid option, try again.");
            }
        }
    }
}
