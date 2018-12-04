package dtos.trulayer;

import java.util.List;

public class TrulayerTransactions {

    public TrulayerTransactions(){}

    private List<TrulayerTransaction> transactions;

    public List<TrulayerTransaction> getTransactions() {
        return transactions;
    }

    public void setTransactions(List<TrulayerTransaction> transactions) {
        this.transactions = transactions;
    }
}
