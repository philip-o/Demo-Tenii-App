package builders;

import dtos.TellerLogin;
import dtos.Transaction;

import java.time.LocalDate;

public class TellerLoginBuilder {

    private String email;
    private String password;
    private String ipAddress;

    public TellerLoginBuilder withPassword(String password) {
        this.password = password;
        return this;
    }

    public TellerLoginBuilder withEmail(String email) {
        this.email = email;
        return this;
    }

    public TellerLoginBuilder withIPAddress(String ipAddress) {
        this.ipAddress = ipAddress;
        return this;
    }

    public TellerLogin buildObject() {
        TellerLogin request = new TellerLogin();
        request.setEmail(this.email);
        request.setPassword(this.password);
        request.setIpAddress(this.ipAddress);
        return request;
    }
}
