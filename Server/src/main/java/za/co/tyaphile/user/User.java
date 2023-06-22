package za.co.tyaphile.user;

import za.co.tyaphile.account.Account;
import za.co.tyaphile.account.AccountType;
import za.co.tyaphile.card.Card;
import za.co.tyaphile.transact.Transactions;

import java.util.ArrayList;
import java.util.List;

public class User {
    private Card card;
    private final Transactions transactions;
    private final Account account;

    private List<Card> cards = new ArrayList<>();

    public User(String name, String surname, AccountType account_type) {
        account = new Account(name, surname, account_type);
        account.GenerateAccount();
        transactions = new Transactions(this);
    }

    public void issueCard() {
        if (card != null) {
            card.setSTOP(true, "Replacement");
        }
        card = new Card(this, account);
        cards.add(card);
    }

    public Account getAccount() {
        return account;
    }

    public void setStop(Card card, String reason) {
        for(Card c:cards) {
            if(c == card) {
                c.setSTOP(true, reason);
                System.out.println("Card: " + c.formatCardNumber(c.getCardNumber()) + " has been temporarily stopped");
            }
        }
    }

    public void removeStop(Card card, String reason) {
        for (Card c : cards) {
            if (c == card) {
                c.setSTOP(false, reason);
                System.out.println("Temporal stop on card: " + c.formatCardNumber(card.getCardNumber()) + " has been uplifted");
            }
        }
    }

    public void setFraudStop(Card card, String reason) {
        for(Card c:cards) {
            if(c == card) {
                c.setFRAUD(true);
                c.setSTOP(true, reason);
            }
        }
    }

    public List<Card> getAllCards() {
        return cards;
    }

    public Card getLastCardIssued() {
        return card;
    }

    public Transactions getTransactions() {
        return transactions;
    }

    public static String returnNumbersOnly(String val) {
        String v = val.replaceAll("(\\s*)(\\^d.)","");
        return  v;
    }
}