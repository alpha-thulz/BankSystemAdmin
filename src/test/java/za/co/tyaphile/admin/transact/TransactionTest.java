package za.co.tyaphile.admin.transact;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TransactionTest {
    private long beneficiary = 510000001;
    @Test
    public void testDeposit() {
        Transaction transaction = new Transaction(beneficiary);
        double amount = transaction.getBalance();
        assertEquals(0, amount);
        transaction.setDeposit(100);
        amount = transaction.getBalance();
        assertEquals(100, amount);
    }
    @Test
    public void testWithdrawal() {
        Transaction transaction = new Transaction(beneficiary);
        double amount = transaction.getBalance();
        assertEquals(0, amount);
        transaction.setDeposit(-100);
        amount = transaction.getBalance();
        assertEquals(-100, amount);
    }

    @Test
    public void testTransactionHistory() {

    }
}
