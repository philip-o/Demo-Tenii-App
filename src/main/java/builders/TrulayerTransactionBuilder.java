package builders;

import dtos.Transaction;
import dtos.trulayer.TrulayerTransaction;

import java.time.LocalDate;

public class TrulayerTransactionBuilder {

    private String accountId;

    public TrulayerTransactionBuilder withAccountid(String accountId) {
        this.accountId = accountId;
        return this;
    }

    public TrulayerTransaction buildObject() {
        TrulayerTransaction transaction = new TrulayerTransaction();
        transaction.setAccountId(this.accountId);
        return transaction;
    }
}
