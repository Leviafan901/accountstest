package by.tut.accounttests.testlisteners.reporter;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.IReporter;
import org.testng.IResultMap;
import org.testng.ISuite;
import org.testng.ISuiteResult;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.TestListenerAdapter;
import org.testng.xml.XmlSuite;
import by.tut.accounttests.data.AccountsMap;

/**
 * This class create .html report after all tests execution and put this report in project root folder to the next path: "test-output/report/..."
 * Also this class create screenshot of the browser window on failed tests and put it into the net folder: "test-output/Screenshots/..."
 */
public class ReportListener extends TestListenerAdapter implements IReporter {

	private final static Logger LOGGER = Logger.getLogger(ReportListener.class);
	
	private static final String ACCOUNTS_ATTRIBUTE = "Accounts";
	private static PrintWriter printWriter;

	@Override
	public void generateReport(List<XmlSuite> xmlSuites, List<ISuite> suites, String outputDirectory) {
		try {
			printWriter = createWriter();
		} catch (IOException e) {
			LOGGER.error("Can't create printWriter and write report!", e);
		}
		startHtmlPage(printWriter);
		generateTestResults(suites, printWriter);
		endHtmlPage(printWriter);
		printWriter.flush();
		printWriter.close();
	}

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
	}

	private PrintWriter createWriter() throws IOException {
		String currentDate = new SimpleDateFormat("yyyy-MM-dd HH-mm-ss").format(new Date());
		String reportPath = "test-output/report";
		String reportName = "test-report-" + currentDate + ".html";
		new File(reportPath).mkdirs();
		return new PrintWriter(new BufferedWriter(new FileWriter(new File(reportPath, reportName))));
	}

	/** Starts HTML Stream */
	private void startHtmlPage(PrintWriter out) {
		out.println("<!DOCTYPE html>");
		out.println("<html>");
		out.println("<head>");
		out.println("<title>Test Report</title>");
		out.println("<style>");
		out.println("TABLE {");
		out.println("width: 100%;");
		out.println("border-collapse: collapse;}");
		out.println("TD, TH {");
		out.println("padding: 3px; \r\n" + "    border: 1px solid black;}");
		out.println("</style>");
		out.println("<head>");
		out.println("<body>");
		out.println("<h3>Test results</h3>");
		out.println("<table>");
		out.println("<tr>");
		out.println("<th width=\"10%\">Account</th>");
		out.println("<th width=\"10%\">Results(Passed/Failed)</th>");
		out.println("<th width=\"80%\">Failures reasons</th>");
		out.println("</tr>");

		out.flush();
	}

	/** Finishes HTML Stream */
	private void endHtmlPage(PrintWriter out) {
		out.println("</table></body></html>");
	}

	private void generateTestResults(List<ISuite> suites, PrintWriter out) {

		for (ISuite suite : suites) {
			Map<String, ISuiteResult> suiteResults = suite.getResults();
			for (ISuiteResult suiteResult : suiteResults.values()) {
				ITestContext testContext = suiteResult.getTestContext();
				AccountsMap accountsMap = (AccountsMap) testContext.getAttribute(ACCOUNTS_ATTRIBUTE);
				printPassedResults(testContext, out, accountsMap);
				printFailedResults(testContext, out, accountsMap);
			}
		}
	}

	private void printFailedResults(ITestContext testContext, PrintWriter out, AccountsMap accountsMap) {
		IResultMap resultFailedMap = testContext.getFailedTests();
		Set<ITestResult> testsResults = resultFailedMap.getAllResults();
		for (ITestResult testResult : testsResults) {
			Throwable throwableCause = testResult.getThrowable();
			String errorMessage = throwableCause.getMessage();
			String testName = testResult.getName();
			String accountEmail = accountsMap.getByTestMethodName(testName);
			out.println("<tr>" + "<th>" + accountEmail + "</th>" + "<th>FAILED</th>" + "<th>" + errorMessage
					+ "</th></tr>");
		}
	}

	private void printPassedResults(ITestContext testContext, PrintWriter out, AccountsMap accountsMap) {
		IResultMap resultFailedMap = testContext.getPassedTests();
		Set<ITestResult> testsResults = resultFailedMap.getAllResults();
		for (ITestResult testResult : testsResults) {
			String testName = testResult.getName();
			String accountEmail = accountsMap.getByTestMethodName(testName);
			out.println("<tr>" + "<th>" + accountEmail + "</th>" + "<th>PASSED</th>" + "<th></th></tr>");
		}
	}
}
