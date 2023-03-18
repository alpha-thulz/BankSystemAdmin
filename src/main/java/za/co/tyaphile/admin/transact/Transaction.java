package za.co.tyaphile.admin.transact;

public class Transaction {
    private double balance;

    public double getBalance() {
        return balance;
    }

    public synchronized void setDeposit(double amount) {
        this.balance += amount;
    }
}
