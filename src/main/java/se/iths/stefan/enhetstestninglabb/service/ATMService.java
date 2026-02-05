package se.iths.stefan.enhetstestninglabb.service;

import org.springframework.stereotype.Service;
import se.iths.stefan.enhetstestninglabb.component.AccountComponent;
import se.iths.stefan.enhetstestninglabb.exception.InsufficientFundsException;
import se.iths.stefan.enhetstestninglabb.exception.InvalidAmountException;
import se.iths.stefan.enhetstestninglabb.exception.MaxWithdrawalExceededException;

@Service
public class ATMService {
    private final AccountComponent accountComponent;
    int maxWithdrawal = 10000;
    int lowestInt = 0;

    public ATMService(AccountComponent accountComponent) {
        this.accountComponent = accountComponent;
    }

    public void performDeposit(int a) {
        if (a <= lowestInt) {
            throw new InvalidAmountException("Can't put in a negative number");
        }
        accountComponent.deposit(a);
    }

    public void performWithdrawal(int a) {
        if (a > accountComponent.getBalance()) {
            throw new InsufficientFundsException("Can't withdraw more than your balance");
        } else if (a > maxWithdrawal) {
            throw new MaxWithdrawalExceededException("can't withdraw more than 10000 at once");
        } else if (a <= lowestInt) {
            throw new InvalidAmountException("Withdrawal has to be bigger than 0");
        }
        accountComponent.withdraw(a);
    }

    public int performDisplayBalance() {
        return accountComponent.displayBalance();

    }
}
