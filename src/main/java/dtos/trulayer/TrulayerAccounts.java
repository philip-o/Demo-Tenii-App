package dtos.trulayer;

import java.util.List;

public class TrulayerAccounts {

    public TrulayerAccounts() {}

    private List<Account> accounts;

    private String accessToken;

    private String refreshToken;

    private String teniiId;

    public List<Account> getAccounts() {
        return accounts;
    }

    public void setAccounts(List<Account> results) {
        this.accounts = results;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public String getRefreshToken() {
        return refreshToken;
    }

    public void setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }

    public String getTeniiId() {
        return teniiId;
    }

    public void setTeniiId(String teniiId) {
        this.teniiId = teniiId;
    }
}
