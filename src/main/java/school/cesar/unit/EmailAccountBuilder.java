package school.cesar.unit;

import java.time.LocalDate;

public class EmailAccountBuilder {

    public EmailAccountBuilder setUser(String user) {
        this.user = user;
        return this;
    }

    public EmailAccountBuilder setDomain(String domain) {
        this.domain = domain;
        return this;
    }

    public EmailAccountBuilder setPassword(String password) {
        this.password = password;
        return this;
    }

    public EmailAccountBuilder setLastPasswordUpdate(LocalDate lastPasswordUpdate) {
        this.lastPasswordUpdate = lastPasswordUpdate;
        return this;
    }

    public EmailAccount build(){

        return new EmailAccount(user, domain, password, lastPasswordUpdate);
    }

    protected String user;
    protected String domain;
    protected String password;
    protected LocalDate lastPasswordUpdate;

    public EmailAccountBuilder(){};



}
