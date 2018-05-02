package by.tut.accounttests.mailer;

import java.util.Objects;

public class Mail {

    private final String mailTitle;
    private final String mailBody;

    public Mail(String mailTitle, String mailBody) {
        this.mailTitle = mailTitle;
        this.mailBody = mailBody;
    }

    public String getMailTitle() {
        return this.mailTitle;
    }

    public String getMailBody() {
        return this.mailBody;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Mail mail = (Mail) o;
        return Objects.equals(getMailTitle(), mail.getMailTitle()) &&
                Objects.equals(getMailBody(), mail.getMailBody());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getMailTitle(), getMailBody());
    }
}
