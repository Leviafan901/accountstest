package by.tut.accounttests.util;

import io.github.bonigarcia.wdm.WebDriverManager;
import net.lightbody.bmp.BrowserMobProxy;
import net.lightbody.bmp.BrowserMobProxyServer;
import net.lightbody.bmp.client.ClientUtil;
import net.lightbody.bmp.proxy.CaptureType;
import org.openqa.selenium.Proxy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;

public class WebDriverHandler {

    public static WebDriver loadDriver(DesiredCapabilities capabilities) {
        WebDriverManager.chromedriver().setup();
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.setHeadless(true);
        chromeOptions.setAcceptInsecureCerts(true);
        /*chromeOptions.addArguments("--proxy-server=direct//*", // EPBYMINW1275:9090
                "--proxy-bypass-list=*",
                "window-size=1980,960",
                "--no-sandbox"//,
               // "--ignore-certificate-errors",
               // "--allow-insecure-localhost"
        );

        //System.setProperty("webdriver.chrome.logfile", "D:/github/accountstest/chromedriver_issue.log");*/

        chromeOptions.merge(capabilities);
        chromeOptions.setAcceptInsecureCerts(true);
        return new ChromeDriver(chromeOptions);
    }

    /*public static WebDriver loadDriver(DesiredCapabilities capabilities) {
        WebDriverManager.chromedriver().setup();
        ChromeOptions chromeOptions = new ChromeOptions();
        //chromeOptions.setHeadless(true);
        chromeOptions.addArguments("--proxy-server='direct//*'", // EPBYMINW1275:9090
                "--proxy-bypass-list=*",
                "window-size=1980,960",
                "--no-sandbox",
                "--ignore-certificate-errors",
                "--allow-insecure-localhost");
        chromeOptions.merge(capabilities);
        chromeOptions.setAcceptInsecureCerts(true);

        System.setProperty("webdriver.chrome.logfile", "D:/github/accountstest/chromedriver_issue.log");

        return new ChromeDriver(chromeOptions);
    }*/

    public static Proxy getSeleniumProxy(BrowserMobProxy proxyServer) {
        return ClientUtil.createSeleniumProxy(proxyServer);
    }

    public static BrowserMobProxyServer getProxyServer() {
        BrowserMobProxyServer proxy = new BrowserMobProxyServer();
        proxy.setTrustAllServers(true);
        proxy.setHarCaptureTypes(CaptureType.REQUEST_CONTENT, CaptureType.RESPONSE_CONTENT);
        proxy.start();

        return proxy;
    }

    /*public static WebDriver loadDriver(DesiredCapabilities capabilities) {
		WebDriverManager.chromedriver().setup();
        System.setProperty("webdriver.chrome.logfile", "D:/github/accountstest/chromedriver_issue.log");
		ChromeOptions chromeOptions = new ChromeOptions();
		chromeOptions.setHeadless(true);
        chromeOptions.setAcceptInsecureCerts(true);
		chromeOptions.addArguments("--proxy-server='direct//*'", // EPBYMINW1275:9090
                "--proxy-bypass-list=*",
                "window-size=1980,960",
                //"--no-sandbox",
                //"--ignore-certificate-errors",
                "--allow-insecure-localhost");

        chromeOptions.merge(capabilities);
		return new ChromeDriver(chromeOptions);
	}*/

   /* public static BrowserMobProxyServer createBrowserMobProxy() {

       CertificateAndKeySource existingCertificateSource =
               new KeyStoreFileCertificateSource("PKCS12",
                       new File("./src/test/resources/sslSupport/ca-keystore-rsa.p12"),
                       "key", "password");

       // configure the MitmManager to use the custom KeyStore source
        ImpersonatingMitmManager mitmManager = ImpersonatingMitmManager.builder()
                .rootCertificateSource(existingCertificateSource)
                .trustAllServers(true)
                .build();

        BrowserMobProxyServer browserMobProxy = new BrowserMobProxyServer();
        browserMobProxy.setMitmManager(mitmManager);
        browserMobProxy.start(8082);

        return browserMobProxy;
    }

    public static BrowserMobProxyServer createBrowserMobProxyAndGenerateKeys() {

        CertificateAndKeySource rootCertificateGenerator = RootCertificateGenerator.builder()
                .keyGenerator(new ECKeyGenerator())
                .build();

        // tell the MitmManager to use the root certificate we just generated
        ImpersonatingMitmManager mitmManager = ImpersonatingMitmManager.builder()
                .rootCertificateSource(rootCertificateGenerator)
                .serverKeyGenerator(new ECKeyGenerator())
                .trustAllServers(true)
                .build();

        BrowserMobProxyServer browserMobProxy = new BrowserMobProxyServer();
        browserMobProxy.setMitmManager(mitmManager);
        browserMobProxy.start(8082);

        return browserMobProxy;
    }*/
}
