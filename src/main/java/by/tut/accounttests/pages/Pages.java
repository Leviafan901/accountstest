package by.tut.accounttests.pages;

import org.openqa.selenium.WebDriver;

public class Pages {

    private MailBoxPage mailBoxPage = null;
    private MailTutByPage mailTutByPage = null;
    private WebDriver webDriver;

    public Pages(WebDriver webDriver) {
        this.webDriver = webDriver;
    }

    public MailTutByPage mailTutByPage() {
        if (mailTutByPage == null) {
            mailTutByPage = new MailTutByPage(webDriver);
        }
        return mailTutByPage;
    }

    public MailBoxPage mailBoxPage() {
        if (mailBoxPage == null) {
            mailBoxPage = new MailBoxPage(webDriver);
        }
        return mailBoxPage;
    }
}
