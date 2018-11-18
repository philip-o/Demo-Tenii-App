package dtos.trulayer;

public class Account {

    public Account() {}

    private String account_id;
    private String account_type;
    private AccountNumbers account_number;
    private String currency;
    private Provider provider;

    public String getAccount_id() {
        return account_id;
    }

    public void setAccount_id(String account_id) {
        this.account_id = account_id;
    }

    public String getAccount_type() {
        return account_type;
    }

    public void setAccount_type(String account_type) {
        this.account_type = account_type;
    }

    public AccountNumbers getAccount_number() {
        return account_number;
    }

    public void setAccount_number(AccountNumbers account_number) {
        this.account_number = account_number;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public Provider getProvider() {
        return provider;
    }

    public void setProvider(Provider provider) {
        this.provider = provider;
    }
}
