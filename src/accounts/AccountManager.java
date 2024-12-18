package accounts;

import java.util.ArrayList;
import java.util.List;

public class AccountManager {
    private List<Account> accounts;

    public AccountManager() {
        this.accounts = new ArrayList<>();
    }

    public boolean signUp(String username, String password, String address, String phone, Role role) {
        for (Account account : accounts) {
            if (account.getUsername().equals(username)) {
                System.out.println("Username already exists. Please try again.");
                return false;
            }
        }
        accounts.add(new Account(username, password, address, phone, role));
        System.out.println("Account created successfully.");
        return true;
    }

    public Account logIn(String username, String password) {
        for (Account account : accounts) {
            if (account.getUsername().equals(username) && account.getPassword().equals(password)) {
                System.out.println("Login successful. Welcome, " + username + "!");
                return account;
            }
        }
        System.out.println("Invalid credentials. Please try again.");
        return null;
    }

    public void displayAccounts() {
        if (accounts.isEmpty()) {
            System.out.println("No accounts found.");
        } else {
            System.out.println("Accounts:");
            for (Account account : accounts) {
                System.out.println(account);
            }
        }
    }
}
