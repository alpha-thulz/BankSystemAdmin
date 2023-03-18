package za.co.tyaphile.admin.transact;

import za.co.tyaphile.admin.transact.detail.TransactionDetail;

import java.util.Date;

public class Payments implements TransactionFields {
    private final long account, beneficiary;
    private Date payDate;
    private int branchCode;
    private String description, channel;
    private double amount;

    public Payments(long account, long beneficiary) {
        this.account = account;
        this.beneficiary = beneficiary;
    }

    @Override
    public long getAccount() {
        return account;
    }

    @Override
    public long getBeneficiary() {
        return beneficiary;
    }

    @Override
    public void setPayDate(Date paymentDate) {
        payDate = paymentDate;
    }

    @Override
    public Date getPayDate() {
        return payDate;
    }

    @Override
    public void setAmount(double amount) {
        this.amount = amount;
    }

    @Override
    public double getAmount() {
        return amount;
    }

    @Override
    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String getDescription() {
        return description;
    }

    @Override
    public void setChannel(String channel) {
        this.channel = channel;
    }

    @Override
    public String getChannel() {
        return channel;
    }

    @Override
    public void setBranchCode(String bankName) {
        branchCode = TransactionDetail.getBranchCode(bankName);
    }

    @Override
    public int getBranchCode() {
        return branchCode;
    }
}
