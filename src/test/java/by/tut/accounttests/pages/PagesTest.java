package by.tut.accounttests.pages;

import by.tut.accounttests.data.AccountsMap;
import by.tut.accounttests.data.TestData;
import by.tut.accounttests.domain.UserAccount;
import by.tut.accounttests.mailer.JavaMailer;
import by.tut.accounttests.mailer.Mail;
import by.tut.accounttests.util.BrowserType;
import by.tut.accounttests.util.TestLogger;
import by.tut.accounttests.util.WebDriverHandler;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Factory;
import org.testng.annotations.Test;

public class PagesTest {

	private final static Logger LOGGER = TestLogger.getLogger(PagesTest.class, "pagestest.log");
	
    private static final Mail MAIL = new Mail("Test mail", "Test mail.");

    private Pages pages;
    private WebDriver driver;
    private UserAccount mailer;
    private UserAccount addressee;
    private AccountsMap accountsMap = new AccountsMap();

    @Factory(dataProvider = "accounts", dataProviderClass = TestData.class)
    public PagesTest(UserAccount mailer, UserAccount addressee) {
    	LOGGER.info("Started test suite!");
    	LOGGER.info("Test will be executed with next test data: " + addressee.getEmail() + ", " + mailer.getEmail());
    	this.mailer = mailer;
        this.addressee = addressee;
    }

    @BeforeClass(alwaysRun = true)
    public void setup(ITestContext testContext)  {
    	LOGGER.info("Step 1. Send mail to " + addressee.getEmail() + " with Java Mail Api.");
        //JavaMailer.sendMail(mailer, addressee, MAIL);
        
        driver = WebDriverHandler.loadDriver(BrowserType.CHROME);
        pages = PageFactory.initElements(driver, Pages.class);
        testContext.setAttribute("WebDriver", this.driver);// set driver for listeners
        accountsMap.add("shouldCheckMailInSentFolderAndReturnTrue", mailer.getEmail());
        accountsMap.add("shouldCheckMailInboxFolderAndReturnTrue", addressee.getEmail());
        testContext.setAttribute("Accounts", accountsMap);// set data for end report
    }

    @Test
    public void shouldCheckMailInSentFolderAndReturnTrue() {
    	LOGGER.info("Step 2. Load page tiwh url http://mail.tut.by.");
        pages.mailTutByPage().loadPage();
        LOGGER.info("Step 3. Login mailbox with account " + mailer.getEmail());
        pages.mailTutByPage().logIn(mailer);
        LOGGER.info("Step 4. Get into sent folder of account " + mailer.getEmail());
        pages.mailBoxPage().getIntoSentFolder();
        LOGGER.info("Step 5. Check sent messages to the account " + addressee.getEmail());
        boolean isSentMail = pages.mailBoxPage().checkMail(addressee, MAIL);
       // LOGGER.info("Step 6. Delete all sented messages, clear up for next test class instance.");
        //pages.mailBoxPage().deleteMessages();
        LOGGER.info("Step 7. Logout from account" + mailer.getEmail());
        pages.mailBoxPage().logOut();

        Assert.assertTrue(isSentMail);
    }

    @Test
    public void shouldCheckMailInboxFolderAndReturnTrue() {
    	LOGGER.info("Step 8. Load page tiwh url http://mail.tut.by.");
        pages.mailTutByPage().loadPage();
        LOGGER.info("Step 9. Login mailbox with account " + addressee.getEmail());
        pages.mailTutByPage().logIn(addressee);
        LOGGER.info("Step 10. Check messages in inbox foldr from account " + mailer.getEmail());
        boolean isInboxMail = pages.mailBoxPage().checkMail(mailer, MAIL);
       // LOGGER.info("Step 11. Delete all sented messages, clear up for next test class instance.");
       // pages.mailBoxPage().deleteMessages();
        LOGGER.info("Step 12. Logout from account" + addressee.getEmail());
        pages.mailBoxPage().logOut();
        
        Assert.assertTrue(isInboxMail);
    }
    
    @AfterClass(alwaysRun = true)
    public void teardown() {
    	LOGGER.info("Test suite is over!");
        driver.quit();
    }
}