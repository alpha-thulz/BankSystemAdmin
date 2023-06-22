package za.co.tyaphile.card;

import za.co.tyaphile.account.Account;
import za.co.tyaphile.user.User;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.ArrayList;
import java.util.List;

public class Card {
    private int bin_number = 519629;
    private String cardNum, stopReason;

    private boolean STOP, FRAUD;
    private List<String> notes = new ArrayList<>();

    CardGenerator cg = new CardGenerator();
    private User user;
    private final Account account;

    public Card(User user, Account account) {
        this.user = user;
        this.account = account;
        cardNum = cg.getCard();
    }

    public String getCardNumber() {
        String combine = bin_number + cardNum;
        Double num = Double.parseDouble(combine);
        return String.valueOf(num);
    }

    public String getCVV() {
        return cg.getCvv();
    }

    public String getCardPin() {
        return cg.getPin();
    }

    public String getStopReason() {
        return stopReason;
    }

    public String formatCardNumber(String number) {
        DecimalFormatSymbols symbols = DecimalFormatSymbols.getInstance();
        symbols.setGroupingSeparator(' ');
        DecimalFormat df = new DecimalFormat("####,####",symbols);
        Double num = Double.parseDouble(number);
        return df.format(num);
    }

    public double getLinkedAccount() {
        return account.getAccountNumber();
    }

    public boolean isSTOPPED() {
        return STOP;
    }

    public void setSTOP(boolean STOP, String reason) {
        if ((!STOP && !isFRAUD()) || STOP) {
            this.stopReason = reason;
            notes.add(reason);
            this.STOP = STOP;
        }
    }

    public boolean isFRAUD() {
        return FRAUD;
    }

    public void setFRAUD(boolean FRAUD) {
        setSTOP(FRAUD, "Fraud on card");
        this.FRAUD = FRAUD;
    }

    public List<String> getNotes() {
        return notes;
    }
}