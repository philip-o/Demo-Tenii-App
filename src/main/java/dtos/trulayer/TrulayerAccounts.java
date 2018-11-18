package dtos.trulayer;

import java.util.List;

public class TrulayerAccounts {

    public TrulayerAccounts() {}

    private List<Account> accounts;

    public List<Account> getAccounts() {
        return accounts;
    }

    public void setResults(List<Account> results) {
        this.accounts = results;
    }
}
