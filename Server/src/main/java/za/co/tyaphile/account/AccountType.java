package za.co.tyaphile.account;

public enum AccountType {
    SAVINGS("Savings"), CHEQUE("Cheque");
    private final String accountType;

    AccountType(String accountType) {
        this.accountType = accountType;
    }

    public String getAccountType() {
        return accountType;
    }
}
