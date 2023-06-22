package za.co.tyaphile.card;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import za.co.tyaphile.account.Account;
import za.co.tyaphile.account.AccountType;
import za.co.tyaphile.card.Card;
import za.co.tyaphile.user.User;

import static org.junit.jupiter.api.Assertions.*;

public class TestCard {

    private Account account;
    private User user;
    private Card card;

    @BeforeEach
    void setupAccount() {
        account = new Account("John", "Doe", AccountType.SAVINGS);
        user = new User(account.getName(), account.getSurname(), account.getAccountType());
        card = new Card(user, account);
    }

    @Test
    void testCardCVV() {
        String origCVV = card.getCVV();
        assertEquals(3, origCVV.length());
        card = new Card(user, account);
        assertFalse(origCVV.equals(card.getCVV()));
    }

    @Test
    void testCardPIN() {
        String origPIN = card.getCardPin();
        assertEquals(4, origPIN.length());
        card = new Card(user, account);
        assertFalse(origPIN.equals(card.getCardPin()));
    }

    @Test
    void testLinkedAccount() {
        assertTrue(account.getAccountNumber() == card.getLinkedAccount());
    }

    @Test
    void testCardFraud() {
        assertFalse(card.isSTOPPED());
        assertFalse(card.isFRAUD());
        card.setSTOP(true, "Misplaced");
        assertTrue(card.isSTOPPED());
        assertFalse(card.isFRAUD());
        card.setSTOP(false, "Found");
        assertFalse(card.isSTOPPED());
        assertFalse(card.isFRAUD());
        card.setFRAUD(true);
        assertTrue(card.isSTOPPED());
        assertTrue(card.isFRAUD());
    }
}