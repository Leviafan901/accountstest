package by.tut.accounttests.util;

import io.github.bonigarcia.wdm.WebDriverManager;
import net.lightbody.bmp.BrowserMobProxyServer;
import net.lightbody.bmp.mitm.CertificateAndKeySource;
import net.lightbody.bmp.mitm.KeyStoreFileCertificateSource;
import net.lightbody.bmp.mitm.manager.ImpersonatingMitmManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.io.File;

public class WebDriverHandler {

    private static BrowserMobProxyServer browserMobProxy;

	public static WebDriver loadDriver(DesiredCapabilities capabilities) {
		WebDriverManager.chromedriver().setup();
		ChromeOptions chromeOptions = new ChromeOptions();
		chromeOptions.setHeadless(true);
        chromeOptions.setAcceptInsecureCerts(true);
        chromeOptions.addArguments("test-type");
		chromeOptions.addArguments("--proxy-server='direct://'",
                "--proxy-bypass-list=*",
                "--start-maximized",
               "window-size=1980,960",
                "--no-sandbox",
                "--ignore-certificate-errors",
                "--allow-insecure-localhost");
        //chromeOptions.addArguments("--acceptSslCerts=true");
        //chromeOptions.addArguments("--disable-web-security");

        chromeOptions.merge(capabilities);
		return new ChromeDriver(chromeOptions);
	}

    public static BrowserMobProxyServer createBrowserMobProxySingleton() {

       CertificateAndKeySource existingCertificateSource =
               new KeyStoreFileCertificateSource("PKCS12",
                       new File("./src/test/resources/sslSupport/ca-keystore-rsa.p12"),
                       "key", "password");

       // configure the MitmManager to use the custom KeyStore source
        ImpersonatingMitmManager mitmManager = ImpersonatingMitmManager.builder()
                .rootCertificateSource(existingCertificateSource)
               .trustAllServers(true)
                .build();

        browserMobProxy = new BrowserMobProxyServer();
        browserMobProxy.setMitmManager(mitmManager);
        browserMobProxy.start(0);

        return browserMobProxy;
    }
}
