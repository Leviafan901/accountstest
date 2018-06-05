package by.tut.accounttests.pages;

import by.tut.accounttests.data.TestData;
import by.tut.accounttests.domain.UserAccount;
import by.tut.accounttests.mailer.JavaMailer;
import by.tut.accounttests.mailer.Mail;
import by.tut.accounttests.util.TestLogger;
import by.tut.accounttests.util.WebDriverHandler;
import net.lightbody.bmp.BrowserMobProxyServer;
import net.lightbody.bmp.client.ClientUtil;
import net.lightbody.bmp.core.har.Har;
import net.lightbody.bmp.proxy.CaptureType;
import org.apache.log4j.Logger;
import org.openqa.selenium.Proxy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Factory;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class PagesTest {

    private final static Logger LOGGER = TestLogger.getLogger(PagesTest.class);
	
    private static final Mail MAIL = new Mail("Test mail", "Test mail.");
    private static final String DATE_FORMAT_PATTERN = "yyyy-MM-dd HH-mm-ss";

    private Pages pages;
    private WebDriver driver;
    private BrowserMobProxyServer proxy;
    private UserAccount mailer;
    private UserAccount addressee;
    private Har har;

    @Factory(dataProvider = "accounts", dataProviderClass = TestData.class)
    public PagesTest(UserAccount mailer, UserAccount addressee) {
    	this.mailer = mailer;
        this.addressee = addressee;
    }

    @BeforeClass(alwaysRun = true)
    public void setup(ITestContext testContext)  {
        LOGGER.info("Started test suite!");
        LOGGER.info("Test will be executed with next test data: " + addressee.getEmail() + ", " + mailer.getEmail());
    	LOGGER.info("Step 1. Send mail to " + addressee.getEmail() + " with Java Mail Api.");
        JavaMailer.sendMail(mailer, addressee, MAIL);

        proxy = WebDriverHandler.createBrowserMobProxySingleton();
      // proxy = new BrowserMobProxyServer();
      // proxy.start(8082);

        Proxy seleniumProxy = ClientUtil.createSeleniumProxy(proxy);
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability(CapabilityType.PROXY, seleniumProxy);
//        capabilities.setCapability(CapabilityType.SUPPORTS_NETWORK_CONNECTION, true);
//        capabilities.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);

        driver = WebDriverHandler.loadDriver(capabilities);
        pages = PageFactory.initElements(driver, Pages.class);
        testContext.setAttribute("WebDriver", this.driver);// set driver for listeners

        proxy.enableHarCaptureTypes(CaptureType.REQUEST_CONTENT, CaptureType.RESPONSE_CONTENT);
    }

    @Test
    public void shouldCheckMailInSentFolderAndReturnTrue() {
        proxy.newHar("TestRun - " +  new SimpleDateFormat(DATE_FORMAT_PATTERN).format(new Date()));

    	LOGGER.info("Step 2. Load page with url http://mail.tut.by.");
        proxy.newPage("http://mail.tut.by");
        pages.mailTutByPage().loadPage();
        LOGGER.info("Step 3. Login mailbox with account " + mailer.getEmail());
        pages.mailTutByPage().logIn(mailer);
        proxy.endPage();
        proxy.newPage("yandex.by - logged account" + mailer.getEmail());
        LOGGER.info("Step 4. Get into sent folder of account " + mailer.getEmail());
        pages.mailBoxPage().getIntoSentFolder();
        LOGGER.info("Step 5. Check sent messages to the account " + addressee.getEmail());
        boolean isSentMail = pages.mailBoxPage().checkMail(addressee, MAIL);
        LOGGER.info("Step 6. Delete all sented messages, clear up for next test class instance.");
        pages.mailBoxPage().deleteMessages();
        LOGGER.info("Step 7. Logout from account " + mailer.getEmail());
        pages.mailBoxPage().logOut();
        proxy.endPage();

        Assert.assertTrue(isSentMail);
    }

    @Test
    public void shouldCheckMailInboxFolderAndReturnTrue() {
    	LOGGER.info("Step 8. Load page tiwh url http://mail.tut.by.");
        pages.mailTutByPage().loadPage();
        proxy.newPage("http://mail.tut.by");
        LOGGER.info("Step 9. Login mailbox with account " + addressee.getEmail());
        pages.mailTutByPage().logIn(addressee);
        proxy.newPage("Logged into mailbox - yandex.by, account " + addressee.getEmail());
        LOGGER.info("Step 10. Check messages in inbox foldr from account " + mailer.getEmail());
        boolean isInboxMail = pages.mailBoxPage().checkMail(mailer, MAIL);
        LOGGER.info("Step 11. Delete all sented messages, clear up for next test class instance.");
        pages.mailBoxPage().deleteMessages();
        LOGGER.info("Step 12. Logout from account " + addressee.getEmail());
        pages.mailBoxPage().logOut();
        proxy.endPage();

        har = proxy.getHar();
        
        Assert.assertTrue(isInboxMail);
    }
    
    @AfterClass(alwaysRun = true)
    public void teardown() {
    	LOGGER.info("Test suite is over!");


        File harFile = new File("test-output/TestRun - " +  new SimpleDateFormat(DATE_FORMAT_PATTERN).format(new Date()) + ".har");
        try {
            harFile.createNewFile();
            har.writeTo(harFile);
        } catch (IOException e) {
            LOGGER.error(e);
        }
        proxy.stop();
        driver.quit();
    }
}