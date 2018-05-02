package by.tut.accounttests.mailer;

import by.tut.accounttests.domain.UserAccount;
import by.tut.accounttests.mailer.JavaMailer;
import by.tut.accounttests.mailer.Mail;
import org.testng.Assert;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

public class JavaMailerTest {

    private static final Mail MAIL = new Mail("Test mail", "Test mail.");
    private UserAccount mailer;
    private UserAccount addressee;

    @BeforeSuite
    public void setup() {
        mailer = new UserAccount("leha.sosenkov", "leha.sosenkov@tut.by", "223223223");
        addressee = new UserAccount("alexei.sosenkov", "alexei.sosenkov@tut.by", "223223223223");
    }

    @Test
    public void shouldSendMailToCorrectEmailBoxWhenGetRightData() {
        //when
        boolean isSent = JavaMailer.sendMail(mailer, addressee, MAIL);
        //then
        Assert.assertTrue(isSent);
    }
}
