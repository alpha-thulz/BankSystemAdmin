package za.co.tyaphile.account;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class TestAccountGenerate {

    @Test
    void testAccountGenerate() {
        AccountGenerate generate = new AccountGenerate();
        assertTrue(generate.getAccountNumber() >= 100_000_000);
        assertTrue(generate.getAccountNumber() <= 999_999_999);
    }
}
