package dtos.trulayer;

import java.util.List;

public class TrulayerAccounts {

    public TrulayerAccounts() {}

    private List<Account> accounts;

    private String accessToken;

    public List<Account> getAccounts() {
        return accounts;
    }

    public void setResults(List<Account> results) {
        this.accounts = results;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }
}
