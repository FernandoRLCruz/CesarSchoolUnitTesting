package school.cesar.unit;

import java.time.Instant;
import java.time.LocalDate;
import java.util.Collection;

public class EmailBuilder {

    public EmailBuilder setCreationDate(LocalDate creationDate) {
        this.creationDate = creationDate;
        return this;
    }

    public EmailBuilder setFrom(String from) {
        this.from = from;
        return this;
    }

    public EmailBuilder setTo(Collection<String> to) {
        this.to = to;
        return this;
    }

    public EmailBuilder setCc(Collection<String> cc) {
        this.cc = cc;
        return this;
    }

    public EmailBuilder setBcc(Collection<String> bcc) {
        this.bcc = bcc;
        return this;
    }

    public EmailBuilder setSubject(String subject) {
        this.subject = subject;
        return this;
    }

    public EmailBuilder setMessage(String message) {
        this.message = message;
        return this;
    }


    public Email build(){

        return new Email(creationDate,from,to,cc,bcc,subject,message);

    }

    public EmailBuilder(){};

    protected LocalDate creationDate;
    protected String from;
    protected Collection<String> to;
    protected Collection<String> cc;
    protected Collection<String> bcc;
    protected String subject;
    protected String message;




}
