package dtos;

public class SourceBankAccount {

    public SourceBankAccount() {}

    private String accountId;
    private String teniiId;

    public String getAccountId() {
        return accountId;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }

    public String getTeniiId() {
        return teniiId;
    }

    public void setTeniiId(String teniiId) {
        this.teniiId = teniiId;
    }
}
