package builders;

import dtos.Login;

public class LoginBuilder {

    private String email;
    private String password;
    private String ipAddress;

    public LoginBuilder withPassword(String password) {
        this.password = password;
        return this;
    }

    public LoginBuilder withEmail(String email) {
        this.email = email;
        return this;
    }

    public LoginBuilder withIPAddress(String ipAddress) {
        this.ipAddress = ipAddress;
        return this;
    }

    public Login buildObject() {
        Login request = new Login();
        request.setEmail(this.email);
        request.setPassword(this.password);
        request.setIpAddress(this.ipAddress);
        return request;
    }
}
