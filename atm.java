import java.util.ArrayList;
import java.util.Scanner;

class Account {
    String name;
    String pin;
    double savingsBalance;
    double currentBalance;
    ArrayList<String> savingsHistory;
    ArrayList<String> currentHistory;

    public Account(String name, String pin) {
        this.name = name;
        this.pin = pin;
        this.savingsBalance = 0;
        this.currentBalance = 0;
        this.savingsHistory = new ArrayList<>();
        this.currentHistory = new ArrayList<>();
    }
}

public class atm {
    static Scanner scanner = new Scanner(System.in);
    static Account user;

    public static void createAccount() {
        System.out.println("---- Account Creation ----");
        System.out.print("Enter your name: ");
        String name = scanner.nextLine();
        System.out.print("Set your PIN (4 digits): ");
        String pin = scanner.nextLine();
        user = new Account(name, pin);
        System.out.println("Account created successfully!");
    }

    public static boolean login() {
        System.out.println("---- Login ----");
        System.out.print("Enter your PIN: ");
        String enteredPin = scanner.nextLine();
        if (enteredPin.equals(user.pin)) {
            System.out.println("Welcome " + user.name + "!");
            return true;
        } else {
            System.out.println("Incorrect PIN. Try again.");
            return false;
        }
    }

    public static void deposit(String accountType) {
        System.out.print("Enter amount to deposit into " + accountType + " account: ");
        double amount = scanner.nextDouble();
        scanner.nextLine(); 
        if (accountType.equalsIgnoreCase("Savings")) {
            user.savingsBalance += amount;
            user.savingsHistory.add("Deposited " + amount);
        } else if (accountType.equalsIgnoreCase("Current")) {
            user.currentBalance += amount;
            user.currentHistory.add("Deposited " + amount);
        }
        System.out.println("Deposited " + amount + " into " + accountType + " account.");
    }

    public static void withdraw(String accountType) {
        System.out.print("Enter amount to withdraw from " + accountType + " account: ");
        double amount = scanner.nextDouble();
        scanner.nextLine();  
        if (accountType.equalsIgnoreCase("Savings")) {
            if (amount <= user.savingsBalance) {
                user.savingsBalance -= amount;
                user.savingsHistory.add("Withdrew " + amount);
                System.out.println("Withdrew " + amount + " from Savings account.");
            } else {
                System.out.println("Insufficient balance in Savings account.");
            }
        } else if (accountType.equalsIgnoreCase("Current")) {
            if (amount <= user.currentBalance) {
                user.currentBalance -= amount;
                user.currentHistory.add("Withdrew " + amount);
                System.out.println("Withdrew " + amount + " from Current account.");
            } else {
                System.out.println("Insufficient balance in Current account.");
            }
        }
    }

    public static void showHistory() {
        System.out.println("---- Transaction History ----");
        System.out.println("Savings Account Transactions:");
        for (String transaction : user.savingsHistory) {
            System.out.println(transaction);
        }
        System.out.println("Current Account Transactions:");
        for (String transaction : user.currentHistory) {
            System.out.println(transaction);
        }
    }

    public static void changePin() {
        System.out.print("Enter your new PIN (4 digits): ");
        String newPin = scanner.nextLine();
        user.pin = newPin;
        System.out.println("Your PIN has been changed successfully.");
    }

    public static void mainMenu() {
        while (true) {
            System.out.println("\n---- Main Menu ----");
            System.out.println("1. Deposit");
            System.out.println("2. Withdraw");
            System.out.println("3. Transaction History");
            System.out.println("4. Change PIN");
            System.out.println("5. Log Out");
            System.out.println("6. Exit");

            System.out.print("Enter choice (1-6): ");
            String choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    System.out.print("Which account would you like to deposit into? (Savings/Current): ");
                    String depositAccountType = scanner.nextLine();
                    if (depositAccountType.equalsIgnoreCase("Savings") || depositAccountType.equalsIgnoreCase("Current")) {
                        deposit(depositAccountType);
                    } else {
                        System.out.println("Invalid account type. Choose either 'Savings' or 'Current'.");
                    }
                    break;
                case "2":
                    System.out.print("Which account would you like to withdraw from? (Savings/Current): ");
                    String withdrawAccountType = scanner.nextLine();
                    if (withdrawAccountType.equalsIgnoreCase("Savings") || withdrawAccountType.equalsIgnoreCase("Current")) {
                        withdraw(withdrawAccountType);
                    } else {
                        System.out.println("Invalid account type. Choose either 'Savings' or 'Current'.");
                    }
                    break;
                case "3":
                    showHistory();
                    break;
                case "4":
                    changePin();
                    break;
                case "5":
                    System.out.println("Logging out...");
                    return; 
                case "6":
                    System.out.println("Exiting program...");
                    System.exit(0); 
                default:
                    System.out.println("Invalid choice. Please enter a number between 1 and 6.");
            }
        }
    }

    public static void atm() {
        createAccount();  
        while (true) {
            if (login()) {
                mainMenu(); 
            } else {
                System.out.println("Please try logging in again.");
            }
        }
    }

    public static void main(String[] args) {
        atm();  
    }
}