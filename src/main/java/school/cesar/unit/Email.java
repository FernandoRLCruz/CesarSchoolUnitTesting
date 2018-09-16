package school.cesar.unit;

import java.time.Instant;
import java.time.LocalDate;
import java.util.Collection;

public class Email {
    private LocalDate creationDate;
    private String from;
    private Collection<String> to;
    private Collection<String> cc;
    private Collection<String> bcc;
    private String subject;
    private String message;

    public Email(LocalDate creationDate, String from, Collection<String> to, Collection<String> cc, Collection<String> bcc, String subject, String message) {
        this.creationDate = creationDate;
        this.from = from;
        this.to = to;
        this.cc = cc;
        this.bcc = bcc;
        this.subject = subject;
        this.message = message;
    }

    public LocalDate getCreationDate() {
        return creationDate;
    }

    public String getFrom() {
        return from;
    }

    public Collection<String> getTo() {
        return to;
    }

    public Collection<String> getCc() {
        return cc;
    }


    public Collection<String> getBcc() {
        return bcc;
    }


}