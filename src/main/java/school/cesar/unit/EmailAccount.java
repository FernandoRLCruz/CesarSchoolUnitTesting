package school.cesar.unit;

import java.time.Instant;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Calendar;
import java.util.Date;

public class EmailAccount {

    public EmailAccount(String user, String domain, String password, LocalDate lastPasswordUpdate) {
        this.user = user;
        this.domain = domain;
        this.password = password;
        this.lastPasswordUpdate = lastPasswordUpdate;
    }

    public String getUser() {
        return user;
    }

    private String user;

    public String getDomain() {
        return domain;
    }

    private String domain;

    public String getPassword() {
        return password;
    }

    private String password;

    public LocalDate getLastPasswordUpdate() {
        return lastPasswordUpdate;
    }


    private LocalDate lastPasswordUpdate;

    public void setLastPasswordUpdate(LocalDate lastPasswordUpdate) {
        this.lastPasswordUpdate = lastPasswordUpdate;
    }

    public boolean verifyPasswordExpiration(){


        boolean resetpassword = true;
        LocalDate hoje = LocalDate.now();
        DateTimeFormatter formatador = DateTimeFormatter.ofPattern("yyyy/MM/dd");
        hoje.format(formatador);

        final long days = ChronoUnit.DAYS.between(getLastPasswordUpdate(), hoje);
        if (days<=90){
            resetpassword = false;
        }
        return resetpassword;
    }

}
