package se.iths.stefan.enhetstestninglabb.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import se.iths.stefan.enhetstestninglabb.component.AccountComponent;
import se.iths.stefan.enhetstestninglabb.exception.InsufficientFundsException;
import se.iths.stefan.enhetstestninglabb.exception.InvalidAmountException;
import se.iths.stefan.enhetstestninglabb.exception.MaxWithdrawalExceededException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ATMServiceTest {

    @InjectMocks
    ATMService atmService;

    @Mock
    AccountComponent accountComponent;

    @Test
    void shouldPerformDeposit() {
        int deposit = 1000;
        atmService.performDeposit(deposit);
        verify(accountComponent).deposit(1000);
    }

    @Test
    void shouldPerformWithdrawal() {
        when(accountComponent.getBalance()).thenReturn(5000);
        int withdraw = 2000;
        atmService.performWithdrawal(withdraw);
        verify(accountComponent).withdraw(2000);

    }

    @Test
    void shouldReturnCorrectBalance() {
        when(accountComponent.displayBalance()).thenReturn(5000);

        int result = atmService.performDisplayBalance();

        assertEquals(5000, result);
        verify(accountComponent).displayBalance();
    }

    @Test
    void shouldThrowInvalidAmountException() {
        int deposit = 0;
        InvalidAmountException thrownException = assertThrows(InvalidAmountException.class, () -> atmService.performDeposit(deposit));

        assertEquals("Can't put in a negative number", thrownException.getMessage());
    }

    @Test
    void shouldThrowMaxWithdrawalExceededException() {
        when(accountComponent.getBalance()).thenReturn(20000);
        int withdraw = 11000;
        MaxWithdrawalExceededException thrownException = assertThrows(MaxWithdrawalExceededException.class, () -> atmService.performWithdrawal(withdraw));
        assertEquals("can't withdraw more than 10000 at once", thrownException.getMessage());
    }

    @Test
    void shouldThrowInsufficientFundsException() {
        int withdraw = 5000;
        when(accountComponent.getBalance()).thenReturn(3000);
        InsufficientFundsException thrownException = assertThrows(InsufficientFundsException.class, () -> atmService.performWithdrawal(withdraw));
        assertEquals("Can't withdraw more than your balance", thrownException.getMessage());
    }
}