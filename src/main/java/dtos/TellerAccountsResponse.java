package dtos;

import java.util.List;

public class TellerAccountsResponse {

    public TellerAccountsResponse() {}

    private String id;
    private List<TellerAccount> accounts;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<TellerAccount> getAccounts() {
        return accounts;
    }

    public void setAccounts(List<TellerAccount> accounts) {
        this.accounts = accounts;
    }
}
