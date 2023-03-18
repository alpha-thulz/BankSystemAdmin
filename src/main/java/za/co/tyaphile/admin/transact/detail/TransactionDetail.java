package za.co.tyaphile.admin.transact.detail;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;

public class TransactionDetail {
    private final long ACCOUNT, BENEFICIARY;
    public TransactionDetail() {
        this(0);
    }


    public TransactionDetail(final long beneficiary) {
        this(beneficiary, 1234567890);
    }

    public TransactionDetail(final long beneficiary, final long  account) {
        this.ACCOUNT = account;
        this.BENEFICIARY = beneficiary;
    }

    public static int getBranchCode(String bankName) {
        Map<String, Integer> branchCodes = new HashMap<>();
        branchCodes.put("ABSA Bank",632005);
        branchCodes.put("African Bank",430000);
        branchCodes.put("Bank of Athens",410506);
        branchCodes.put("Bidvest Bank",462005);
        branchCodes.put("Capitec Bank",470010);
        branchCodes.put("First National Bank",250655);
        branchCodes.put("Investec",580105);
        branchCodes.put("Post Bank",460005);
        branchCodes.put("Standard Bank",051001);
        branchCodes.put("Nedbank",198765);
        branchCodes.put("Tyme Bank",678910);

        return branchCodes.entrySet().stream().filter(x -> x.getKey().equalsIgnoreCase(bankName)).map(x -> x.getValue()).toList().get(0);
    }
}