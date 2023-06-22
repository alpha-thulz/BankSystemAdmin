package za.co.tyaphile.user;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import za.co.tyaphile.account.AccountType;
import za.co.tyaphile.card.Card;

import static org.junit.jupiter.api.Assertions.*;

public class TestUser {
    User user1;
    @BeforeEach
    void setFirstUser() {
        user1 = new User("John", "Doe", AccountType.SAVINGS);
    }

    @Test
    void testAccountNotNull() {
        assertNotNull(user1.getAccount().getFormattedAccount());
    }

    @Test
    void testAccountNotClosed() {
        assertFalse(user1.getAccount().isClosed());
    }

    @Test
    void testAccountClosed() {
        user1.getAccount().setClosed(true, "Testing");
        assertEquals("Testing", user1.getAccount().getCloseReason());
        assertTrue(user1.getAccount().isClosed());
        user1.getAccount().setClosed(false, "Mistake");
        assertTrue(user1.getAccount().isClosed());
    }

    @Test
    void testAccountNotFrozen() {
        assertFalse(user1.getAccount().isOnHold());
    }

    @Test
    void testAccountFrozen() {
        user1.getAccount().setOnHold(true, "Testing");
        assertEquals("Testing", user1.getAccount().getHoldReason());
        assertTrue(user1.getAccount().isOnHold());
        user1.getAccount().setOnHold(false, "Mistake");
        assertFalse(user1.getAccount().isClosed());
    }

    @Test
    void testHold() {
        user1.issueCard();
        Card origin = user1.getLastCardIssued();
        assertFalse(origin.getCardNumber().isEmpty());
        user1.setStop(user1.getLastCardIssued(), "Misplaced");
        assertTrue(origin.isSTOPPED());
        user1.removeStop(user1.getLastCardIssued(), "Misplaced");
        assertFalse(origin.isSTOPPED());
    }

    @Test
    void testFraudAndNewCard() {
        user1.issueCard();
        Card origin = user1.getLastCardIssued();
        assertFalse(origin.getCardNumber().isEmpty());
        user1.setFraudStop(user1.getLastCardIssued(), "Misplaced");
        assertTrue(origin.isSTOPPED());
        user1.removeStop(user1.getLastCardIssued(), "Misplaced");
        assertTrue(origin.isSTOPPED());
        user1.issueCard();
        assertEquals(2, user1.getAllCards().size());
    }
}
