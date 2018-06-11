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
        chromeOptions.addArguments("--proxy-server=direct//*",
                "window-size=1980,960",
                "--ignore-certificate-errors",
                "--allow-insecure-localhost"
        );

        chromeOptions.merge(capabilities);
        chromeOptions.setAcceptInsecureCerts(true);
        return new ChromeDriver(chromeOptions);
    }

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
}
