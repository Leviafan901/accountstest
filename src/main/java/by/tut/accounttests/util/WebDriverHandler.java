package by.tut.accounttests.util;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.opera.OperaDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

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
	public static WebDriver loadDriver(BrowserType type) {
		switch (type) {
		case FIREFOX:
			WebDriverManager.firefoxdriver().setup();
			return new FirefoxDriver();
		case CHROME:
			WebDriverManager.chromedriver().setup();
			return new ChromeDriver();
		case IE:
			WebDriverManager.iedriver().setup();
			return new InternetExplorerDriver();
		case OPERA:
			WebDriverManager.operadriver().setup();
			return new OperaDriver();
		default:
			WebDriverManager.chromedriver().setup();
			return new ChromeDriver();
		}
	}
}
