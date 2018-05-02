package by.tut.accounttests.pages;

import by.tut.accounttests.domain.UserAccount;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class MailTutByPage extends AbstractPage {

    @FindBy(id = "Username")
    private WebElement loginInputField;

    @FindBy(id = "Password")
    private WebElement passwordInputField;

    @FindBy(css = "input.button.loginButton.gradientforbutton")
    private WebElement loginSubmitButton;

    private static final String URL = "https://mail.tut.by/";
    private WebDriver webDriver;

    public MailTutByPage(WebDriver webDriver) {
    	super(webDriver);
    	this.webDriver = getDriver();
        setPageUrl(URL);
        PageFactory.initElements(this.webDriver, this);
    }

    public void logIn(UserAccount account) {
    	waitUntilElementIsPresent(loginInputField);
    	setElementText(loginInputField, account.getLogin());
    	setElementText(passwordInputField, account.getPassword());
    	clickElement(loginSubmitButton);
    }
}
