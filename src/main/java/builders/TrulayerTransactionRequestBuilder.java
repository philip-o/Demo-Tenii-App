package builders;

import dtos.trulayer.TrulayerTransactionRequest;

public class TrulayerTransactionRequestBuilder {

    private String accountId;

    public TrulayerTransactionRequestBuilder withAccountid(String accountId) {
        this.accountId = accountId;
        return this;
    }

    public TrulayerTransactionRequest buildObject() {
        TrulayerTransactionRequest transaction = new TrulayerTransactionRequest();
        transaction.setAccountId(this.accountId);
        return transaction;
    }
}
