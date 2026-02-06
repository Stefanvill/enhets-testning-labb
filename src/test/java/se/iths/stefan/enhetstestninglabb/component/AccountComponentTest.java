package se.iths.stefan.enhetstestninglabb.component;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class AccountComponentTest {

    @Mock //Separata AccountComponent för att kunna köra mock och junit separat
    AccountComponent mockAccountComponent;
    private AccountComponent accountComponent;

    @BeforeEach
    void setUp() {
        accountComponent = new AccountComponent();  // Ny fräsch instans per test
    }

    @Test
    void displayBalance() {
        when(mockAccountComponent.displayBalance()).thenReturn(999);

        int result = mockAccountComponent.displayBalance();

        assertEquals(999, result);
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
}