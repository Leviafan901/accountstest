package by.tut.accounttests.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public abstract class AbstractPage {

	private WebDriver driver;
	private WebDriverWait wait;
    private String pageUrl;

    public AbstractPage(WebDriver driver){
        this.driver = driver;
        this.wait = new WebDriverWait(driver, 15);
    }
    
    protected void setPageUrl(String pageUrl) {
    	this.pageUrl = pageUrl;
    }

    protected String getPageUrl(){
        return pageUrl;
    }
    
    protected void loadPage(){
        driver.get(pageUrl);
    }

    protected WebDriver getDriver() {
		return driver;
	}

    protected WebDriverWait getWait() {
		return wait;
	}

	protected void waitUntilElementIsPresent(WebElement element){
    	wait.until(ExpectedConditions.visibilityOf(element));
    }

	protected void clickElement(WebElement element){
        element.click();
    }

	protected void setElementText(WebElement element, String text){
        element.clear();
        element.sendKeys(text);
    }
}
