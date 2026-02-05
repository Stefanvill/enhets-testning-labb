package se.iths.stefan.enhetstestninglabb.component;

import org.springframework.stereotype.Component;

@Component
public class AccountComponent {
    private int balance = 0;

    public void deposit(int amount) {
        balance += amount;
        System.out.println("new balance: " + balance);
    }

    public void withdraw(int amount) {
        balance -= amount;
        System.out.println("new balance: " + balance);
    }

    public int displayBalance() {
        System.out.println("Your balance is: " + balance);
        return balance;
    }

    public int getBalance() {
        return balance;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }

}
