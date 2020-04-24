package webdriver;

import util.Config;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

public class WebDriverFactory {
    private static final Config CONFIG = Config.Instance();
    private static DesiredCapabilities driverCapabilities = new DesiredCapabilities();

    {
        driverCapabilities.setCapability("platform", Platform.WINDOWS);
    }

    public static WebDriver getDriver(String browserName) {
        driverCapabilities.setCapability("browser", browserName);

        switch (browserName.toUpperCase()) {
            case "REMOTE":
                return new RemoteWebDriver(driverCapabilities);
            case "LOCAL_CHROME":
                System.setProperty("webdriver.chrome.driver", CONFIG.getLocalDriverPath().toString());
                return new ChromeDriver();
            default:
                throw new UnsupportedOperationException("Browser " + browserName + " isn't supported");
        }
    }
}
