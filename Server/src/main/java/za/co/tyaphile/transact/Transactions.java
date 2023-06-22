package za.co.tyaphile.transact;

import za.co.tyaphile.user.User;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Date;
import java.util.Locale;

public class Transactions {
    private final User user;
    private boolean deduct;

    public Transactions(final User user) {
        this.user = user;
    }

    public User getUser() {
        return user;
    }

    public void Deposit(double amount, String description) {
        BigDecimal overDraft = user.getAccount().getOverDraftLimit().subtract(user.getAccount().getOverDraft());

        if (amount <= overDraft.doubleValue()) {
            user.getAccount().setOverDraft(amount);
            System.out.println("Deposit went into settling overdraft");
            System.out.println(new Date() + ": '" + description + "' into account '" + user.getAccount().getAccountNumber() + "' amount R " + CurrencyFormat(BigDecimal.valueOf(amount)));

        } else {
            double different = 0;
            if (overDraft.doubleValue() > 0) {
                different = amount - overDraft.doubleValue();
                user.getAccount().setOverDraft(different);
            }
            double deposit = amount - different;
            user.getAccount().setBalance(deposit);

            System.out.println(new Date() + ": '" + description + "' into account '" + user.getAccount().getAccountNumber() + "' amount R " + CurrencyFormat(BigDecimal.valueOf(amount)));
            System.out.println("Current balance: R" + user.getAccount().getBalance());
        }
    }

    public void Withdrawal(double amount, String description) {
        if(user.getLastCardIssued().isSTOPPED() || user.getLastCardIssued().isFRAUD()) {
            System.out.println("Cannot transact with card: " + user.getLastCardIssued().formatCardNumber(user.getLastCardIssued().getCardNumber()) + " as there is a stop on it");
            setDeduction(false);
        } else {
            if ((getConvertedBalance("b") < amount) && (getConvertedBalance("d") < amount) && ((getConvertedBalance("b") + getConvertedBalance("d")) < amount)) {
                System.out.println("You have insufficient funds to perform transaction of R" + CurrencyFormat(BigDecimal.valueOf(amount)));
                System.out.println("Available balance: R" + getConvertedBalance("b"));
                System.out.println("Available overdraft: R" + getConvertedBalance("d"));
                setDeduction(false);
            } else {
                setDeduction(true);
                if ((getConvertedBalance("b") < amount)) {
                    String bal = getBalance();

                    double balance = Double.parseDouble(User.returnNumbersOnly(bal));

                    user.getAccount().setBalance(-(amount-(amount-getConvertedBalance("b"))));
                    user.getAccount().setOverDraft(-(amount-balance));
                } else {
                    user.getAccount().setBalance(-amount);
                }
                System.out.println(new Date() + ": '" + description + "' from account '" + user.getAccount().getAccountNumber() + "' amount R " + CurrencyFormat(BigDecimal.valueOf(amount)));
                System.out.println("Remaining balance: R" + CurrencyFormat(user.getAccount().getBalance()));
                System.out.println("Available overdraft: R" + getConvertedBalance("d"));
            }
        }
    }

    private void setDeduction(boolean val) {
        deduct = val;
    }

    public boolean getDeduction() {
        return deduct;
    }

    private double getConvertedBalance(String type) {
        if(type.equalsIgnoreCase("b")) {
            return Double.parseDouble(removeSpaces(getBalance()));
        } else if (type.equalsIgnoreCase("d")){
            return Double.parseDouble(removeSpaces(getOverDraftLimit()));
        }
        return 0;
    }

    public String getBalance() {
        return CurrencyFormat(user.getAccount().getBalance());
    }

    public String getOverDraftLimit() {
        return CurrencyFormat(user.getAccount().getOverDraft());
    }

    public void setOverDraftLimit(double amount) {
        user.getAccount().setOverDraft(amount);
    }

    public String removeSpaces(String val) {
        return val.replaceAll("\\s","");
    }

    public String CurrencyFormat(BigDecimal amount) {
        DecimalFormatSymbols dfs = DecimalFormatSymbols.getInstance(Locale.ENGLISH);
        dfs.setGroupingSeparator(' ');
        DecimalFormat df = new DecimalFormat("#,##0.00",dfs);
        return df.format(amount);
    }
}