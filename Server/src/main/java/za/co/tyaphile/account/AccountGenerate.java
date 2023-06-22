package za.co.tyaphile.account;

import java.util.Random;

public class AccountGenerate {
    private double account;
    private final double min = 10_000_000, max = 99_999_999;

    public AccountGenerate() {
        GenerateAccount();
    }

    public void GenerateAccount() {
        Random r = new Random();
        account = max + ((long) (r.nextDouble() * (max - min)));
    }

    public void setAccountNumber(String accountNumber) {
        account = Double.parseDouble(accountNumber);
    }

    public double getAccountNumber() {
        return account;
    }
}