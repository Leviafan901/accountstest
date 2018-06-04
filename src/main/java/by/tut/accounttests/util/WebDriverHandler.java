package by.tut.accounttests.util;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.opera.OperaDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

public class WebDriverHandler {

	/***
	 * 
	 * Static method, allows to download, startup and create instance of the driver
	 * that we need and what we can choose with help parameters of this method.
	 * For example, we can get chromedriver putting BrowserType.CHROME into method parameters.
	 * 
	 * @param BrowserType type
	 * @return WebDriver
	 */
	public static WebDriver loadDriver(BrowserType type, DesiredCapabilities capabilities) {
		switch (type) {
		case FIREFOX:
			WebDriverManager.firefoxdriver().setup();
            FirefoxOptions firefoxOptions = new FirefoxOptions();
            firefoxOptions.merge(capabilities);
			return new FirefoxDriver(firefoxOptions);
		case CHROME:
			WebDriverManager.chromedriver().setup();
            ChromeOptions chromeOptions = new ChromeOptions();
            chromeOptions.merge(capabilities);
			return new ChromeDriver(chromeOptions);
		case IE:
			WebDriverManager.iedriver().setup();
			return new InternetExplorerDriver();
		case OPERA:
			WebDriverManager.operadriver().setup();
			return new OperaDriver();
		default:
			WebDriverManager.chromedriver().setup();
            ChromeOptions defaultChromeOptions = new ChromeOptions();
            defaultChromeOptions.merge(capabilities);
			return new ChromeDriver(defaultChromeOptions);
		}
	}
}
