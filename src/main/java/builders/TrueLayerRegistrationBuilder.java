package builders;

import dtos.Register;
import dtos.RoarType;

public class TrueLayerRegistrationBuilder {

    private String title;
    private String forename;
    private String surname;
    private String dob;
    private String password;
    private String mobile;
    private String email;
    private RoarType roarType;
    private String ipAddress;
    private String provider;

    public TrueLayerRegistrationBuilder withTitle(String title) {
        this.title = title;
        return this;
    }

    public TrueLayerRegistrationBuilder withForename(String forename) {
        this.forename = forename;
        return this;
    }

    public TrueLayerRegistrationBuilder withSurname(String surname) {
        this.surname = surname;
        return this;
    }

    public TrueLayerRegistrationBuilder withDOB(String dob) {
        this.dob = dob;
        return this;
    }

    public TrueLayerRegistrationBuilder withPassword(String password) {
        this.password = password;
        return this;
    }

    public TrueLayerRegistrationBuilder withMobile(String mobile) {
        this.mobile = mobile;
        return this;
    }

    public TrueLayerRegistrationBuilder withEmail(String email) {
        this.email = email;
        return this;
    }

    public TrueLayerRegistrationBuilder withRoarType(RoarType roar) {
        this.roarType = roar;
        return this;
    }

    public TrueLayerRegistrationBuilder withIPAddress(String ipAddress) {
        this.ipAddress = ipAddress;
        return this;
    }

    public TrueLayerRegistrationBuilder withProvider(String provider) {
        this.provider = provider;
        return this;
    }

    public Register buildObject() {

        Register register = new Register();
        register.setTitle(this.title);
        register.setForename(this.forename);
        register.setSurname(this.surname);
        register.setDob(this.dob);
        register.setPassword(this.password);
        register.setMobile(this.mobile);
        register.setEmail(this.email);
        register.setRoarType(this.roarType);
        register.setIpAddress(this.ipAddress);
        register.setProvider(this.provider);
        return register;
    }
}
