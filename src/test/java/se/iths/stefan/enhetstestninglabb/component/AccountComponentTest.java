package se.iths.stefan.enhetstestninglabb.component;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AccountComponentTest {
    private AccountComponent accountComponent;

    @BeforeEach
    public void setUp() {
        accountComponent = new AccountComponent();
    }

    @Test
    void deposit() {
        int amount = 1000;
        accountComponent.deposit(amount);
        assertEquals(1000, accountComponent.getBalance());
    }

    @Test
    void withdraw() {
        int amount = 1000;
        accountComponent.withdraw(amount);
        assertEquals(-1000, accountComponent.getBalance());
    }

    @Test
    void depositThenWithdraw() {
        int deposit = 1000;
        int withdraw = 500;
        accountComponent.deposit(deposit);
        accountComponent.withdraw(withdraw);
        assertEquals(500, accountComponent.getBalance());
    }

    @Test
    void displayBalance() {
        int result = accountComponent.getBalance();
        assertEquals(0, result);
    }
}