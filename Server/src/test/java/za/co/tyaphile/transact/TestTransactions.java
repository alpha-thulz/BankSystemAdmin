package za.co.tyaphile.transact;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import za.co.tyaphile.account.AccountType;
import za.co.tyaphile.user.User;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestTransactions {
    private User user;
    private Transactions transactions;

    @BeforeEach
    void setupAccount() {
        user = new User("John", "Doe", AccountType.SAVINGS);
        user.issueCard();
        transactions = user.getTransactions();
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));
    }

    @Test
    void testTransactions() {
        assertEquals("0.00", transactions.getBalance());
        transactions.Deposit(1000, "Deposit");
        assertEquals("1 000.00", transactions.getBalance());
        transactions.Withdrawal(500, "Deposit");
        assertEquals("500.00", transactions.getBalance());
    }
}
