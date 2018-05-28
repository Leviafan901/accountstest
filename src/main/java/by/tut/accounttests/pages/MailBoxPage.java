package by.tut.accounttests.pages;

import by.tut.accounttests.domain.UserAccount;
import by.tut.accounttests.mailer.Mail;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.Objects;

public class MailBoxPage extends AbstractPage {

    @FindBy(css = "a[href='#sent']")
    private WebElement sentFolder;

    @FindBy(css = "span.mail-MessageSnippet-FromText")
    private WebElement mailAuthorElement;

    @FindBy(css = "span.mail-MessageSnippet-Item.mail-MessageSnippet-Item_subject")
    private WebElement mailTitleElement;

    @FindBy(css = "div.mail-User-Name")
    private WebElement userMailBoxName;

    @FindBy(css = "a[data-metric= \"Выход\"] ")
    private WebElement logoutButton;
    
    @FindBy(css = "span._nb-checkbox-flag._nb-checkbox-normal-flag")  
    private WebElement selectAllMessagesCheckbox;
    
    @FindBy(css = "div[title = 'Удалить (Delete)']")
    private WebElement deleteButton;

    private WebDriver webDriver;

    public MailBoxPage(WebDriver webDriver) {
        super(webDriver);
    	this.webDriver = getDriver();
        PageFactory.initElements(this.webDriver, this);
    }

    public void getIntoSentFolder() {
    	waitUntilElementIsPresent(sentFolder);
    	clickElement(sentFolder);
    }

    public void logOut() {
    	clickElement(userMailBoxName);
    	waitUntilElementIsPresent(logoutButton);
    	clickElement(logoutButton);
    }
    
    public void deleteMessages() {
    	clickElement(selectAllMessagesCheckbox);
    	waitUntilElementIsPresent(deleteButton);
    	clickElement(deleteButton);
    }

    /***
     * 
     * Method allows to check if the mail exist in some folder. That's mean
     * method check the existence of  mailAuthor element in the box
     * and then compare title of the Mail with title message that is in the box 
     * 
     * @param UserAccount userAccount
     * @param Mail mail
     * @return boolean
     */
    public boolean checkMail(UserAccount userAccount, Mail mail) {
    	waitUntilElementIsPresent(mailAuthorElement);
        String mailAuthor = mailAuthorElement.getText();
        String email = userAccount.getLogin();
        if(Objects.nonNull(mailAuthor) && mailAuthor.equals(email)) {
            String currentMailTitle = mailTitleElement.getText();
            String mailTitle = mail.getMailTitle();
            if (mailTitle.equals(currentMailTitle)) {
                return true;
            }
        }
        return false;
    }
}