package by.tut.accounttests.testlisteners.reporter;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import com.epam.reportportal.message.ReportPortalMessage;
import com.epam.reportportal.service.ReportPortal;
import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.TestListenerAdapter;

/**
 * This class create .html report after all tests execution and put this report in project root folder to the next path: "test-output/report/..."
 * Also this class create screenshot of the browser window on failed tests and put it into the net folder: "test-output/Screenshots/..."
 */
public class ReportListener extends TestListenerAdapter {

    private static final Logger LOGGER = LoggerFactory.getLogger("binary_data_logger");

	@Override
	public void onTestFailure(ITestResult testResult) {
		Object webDriverAttribute = testResult.getTestContext().getAttribute("WebDriver");
		if (webDriverAttribute instanceof WebDriver) {
			try {
				captureFailureScreenshot((WebDriver) webDriverAttribute, testResult.getName());
			} catch (IOException e) {
				LOGGER.error("Can't create scrennshot of the failed test!", e);
			}
		}
	}

	/***
	 * This method capture screenshot of the browser window if test is failed.
	 * 
	 * @param WebDriver driver
	 * @param String testName
	 * @throws IOException
	 */
	private void captureFailureScreenshot(WebDriver driver, String testName) throws IOException {
		TakesScreenshot screenshottingDriver = (TakesScreenshot) driver;
		File scrFile = screenshottingDriver.getScreenshotAs(OutputType.FILE);
		String filePath = "test-output/Screenshots/failures/" + testName + "_"
				+ new SimpleDateFormat("yyyy-MM-dd HH-mm-ss").format(new Date()) + ".jpg";
		FileUtils.copyFile(scrFile, new File(filePath));

		String rpMessage = testName + "_" + new SimpleDateFormat("yyyy-MM-dd HH-mm-ss").format(new Date());
		ReportPortal.emitLog(testName, "INFO", Calendar.getInstance().getTime(), scrFile);
	}
}
