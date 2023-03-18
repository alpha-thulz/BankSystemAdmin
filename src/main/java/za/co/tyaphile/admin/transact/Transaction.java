package za.co.tyaphile.admin.transact;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Transaction {
    private final long account, beneficiary;
    private double balance;
    List<Payments> payments = new ArrayList<>();
    List<Receipts> receipts = new ArrayList<>();

    public Transaction(long beneficiary) {
        this(1234567890, beneficiary);
    }
    public Transaction(long account, long beneficiary) {
        this.account = account;
        this.beneficiary = beneficiary;
    }


    public double getBalance() {
        return balance;
    }

    public synchronized void setDeposit(double amount) {

    }

    public synchronized void setDeposit(double amount, String branch) {
        Payments payment = new Payments(account, beneficiary);
        Receipts receipt = new Receipts(beneficiary, account);

        payment.setChannel("EFT");
        payment.setAmount(-amount);
        payment.setBranchCode(branch);
        payment.setPayDate(new Date());
        payment.setDescription("EFT-On-Us");
    }

    public synchronized void setDeposit(double amount, String description, String branch) {
        Payments payment = new Payments(account, beneficiary);
        Receipts receipt = new Receipts(beneficiary, account);

        payment.setChannel("EFT");
        payment.setAmount(-amount);
        payment.setBranchCode(branch);
        payment.setPayDate(new Date());
        payment.setDescription(description);
    }
}
