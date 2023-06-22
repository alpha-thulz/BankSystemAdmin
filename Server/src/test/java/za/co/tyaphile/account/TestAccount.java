package za.co.tyaphile.account;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static org.junit.jupiter.api.Assertions.*;

public class TestAccount {

    @Test
    void testAccountHolder() {
        Account account = new Account("John", "Smith", AccountType.SAVINGS);
        assertEquals("John", account.getName());
        assertEquals("Smith", account.getSurname());
    }

    @Test
    void testAccountType() {
        Account account = new Account("John", "Smith", AccountType.SAVINGS);
        assertEquals(AccountType.SAVINGS, account.getAccountType());
    }

    @Test
    void testBalance() {
        Account account = new Account("John", "Smith", AccountType.SAVINGS);
        assertEquals(BigDecimal.valueOf(0), account.getBalance());
        account.setBalance(1000);
        assertEquals(BigDecimal.valueOf(1000.00), account.getBalance());
    }

    @Test
    void testOverdraft() {
        Account account = new Account("John", "Smith", AccountType.SAVINGS);
        assertEquals(BigDecimal.valueOf(0), account.getOverDraft());
        assertEquals(BigDecimal.valueOf(0), account.getOverDraftLimit());
        account.setOverDraftLimit(1000);
        assertEquals(BigDecimal.valueOf(1000.0), account.getOverDraftLimit());
    }

    @Test
    void testAccountHold() {
        Account account = new Account("John", "Smith", AccountType.SAVINGS);
        assertFalse(account.isOnHold());
        account.setOnHold(true, "Testing");
        assertTrue(account.isOnHold());
    }

    @Test
    void testAccountClose() {
        Account account = new Account("John", "Smith", AccountType.SAVINGS);
        assertFalse(account.isClosed());
        account.setClosed(true, "Testing");
        assertTrue(account.isClosed());
        account.setClosed(false, "Testing");
        assertTrue(account.isClosed());
    }

    @Test
    void testAccountFormat() {
        for (int i = 0; i < 10000; i++) {
            Account account = new Account("John", "Smith", AccountType.SAVINGS);
            String reg = "^(\\d{3}-){2}\\d{3}$";
            Pattern pattern = Pattern.compile(reg);
            Matcher matcher = pattern.matcher(account.getFormattedAccount());
            assertTrue(matcher.matches());
        }
    }
}