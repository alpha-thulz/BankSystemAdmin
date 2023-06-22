package za.co.tyaphile.account;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.ArrayList;
import java.util.List;

public class Account extends AccountGenerate {

    private String name, surname, holdReason, closeReason;
    private BigDecimal balance = new BigDecimal(0);
    private BigDecimal overDraft = new BigDecimal(0);
    private BigDecimal overDraftLimit = new BigDecimal(0);
    private boolean closed, onHold;
    private AccountType type;
    private List<String> notes = new ArrayList<>();

    public Account(String name, String surname, AccountType type) {
        this.name = name;
        this.surname = surname;
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public AccountType getAccountType() {
        return type;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public synchronized void setBalance(double amount) {
        this.balance = balance.add(BigDecimal.valueOf(amount));
    }

    public BigDecimal getOverDraft() {
        return overDraft;
    }

    public void setOverDraft(double amount) {
        this.overDraft = overDraft.add(BigDecimal.valueOf(amount));
    }

    public BigDecimal getOverDraftLimit() {
        return overDraftLimit;
    }

    public void setOverDraftLimit(double amount) {
        this.overDraftLimit = overDraftLimit.add(BigDecimal.valueOf(amount));
    }

    public boolean isClosed() {
        return closed;
    }

    public void setClosed(boolean closed, String reason) {
        if (!this.closed) {
            this.closeReason = reason;
            notes.add(reason);
            this.closed = closed;
        }
    }

    public boolean isOnHold() {
        return onHold;
    }

    public void setOnHold(boolean onHold, String reason) {
        this.holdReason = reason;
        notes.add(reason);
        this.onHold = onHold;
    }

    public String getHoldReason() {
        return holdReason;
    }

    public String getCloseReason() {
        return closeReason;
    }

    public List<String> getNotes() {
        return notes;
    }

    public String getFormattedAccount() {
        DecimalFormatSymbols symbols = DecimalFormatSymbols.getInstance();
        symbols.setGroupingSeparator('-');
        DecimalFormat df = new DecimalFormat("00,0000,000", symbols);
        double account = super.getAccountNumber();
        return df.format(account);
    }
}