package za.co.tyaphile.admin.transact;

import java.util.Date;

public interface TransactionFields {
    public long getAccount();
    public long getBeneficiary();
    public void setPayDate(Date paymentDate);
    public Date getPayDate();
    public void setAmount(double amount);
    public double getAmount();
    public void setDescription(String description);
    public String getDescription();
    public void setChannel(String channel);
    public String getChannel();
    public void setBranchCode(String bankName);
    public int getBranchCode();
}
