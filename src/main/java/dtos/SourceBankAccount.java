package dtos;

import java.util.Set;

public class SourceBankAccount {

    public SourceBankAccount() {}

    private Set<String> accountIds;
    private String teniiId;

    public Set<String> getAccountIds() {
        return accountIds;
    }

    public void setAccountIds(Set<String> accountIds) {
        this.accountIds = accountIds;
    }

    public String getTeniiId() {
        return teniiId;
    }

    public void setTeniiId(String teniiId) {
        this.teniiId = teniiId;
    }
}
