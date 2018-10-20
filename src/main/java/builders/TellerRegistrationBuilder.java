package builders;

import dtos.Register;
import dtos.RoarType;

public class TellerRegistrationBuilder {

    private String title;
    private String forename;
    private String surname;
    private String dob;
    private String password;
    private String mobile;
    private String email;
    private RoarType roarType;
    private String ipAddress;

    public TellerRegistrationBuilder withTitle(String title) {
        this.title = title;
        return this;
    }

    public TellerRegistrationBuilder withForename(String forename) {
        this.forename = forename;
        return this;
    }

    public TellerRegistrationBuilder withSurname(String surname) {
        this.surname = surname;
        return this;
    }

    public TellerRegistrationBuilder withDOB(String dob) {
        this.dob = dob;
        return this;
    }

    public TellerRegistrationBuilder withPassword(String password) {
        this.password = password;
        return this;
    }

    public TellerRegistrationBuilder withMobile(String mobile) {
        this.mobile = mobile;
        return this;
    }

    public TellerRegistrationBuilder withEmail(String email) {
        this.email = email;
        return this;
    }

    public TellerRegistrationBuilder withRoarType(RoarType roar) {
        this.roarType = roar;
        return this;
    }

    public TellerRegistrationBuilder withIPAddress(String ipAddress) {
        this.ipAddress = ipAddress;
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
        return register;
    }
}
