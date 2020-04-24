package base;

import org.openqa.selenium.WebDriver;
import util.Config;
import webdriver.WebDriverFactory;

public class InitTest {
    protected final Config CONFIG = Config.Instance();

    public WebDriver getWebDriverInstance() {
        var driver = WebDriverFactory.getDriver(CONFIG.getDriver());
        driver.manage().window().maximize();
        return driver;
    }
}

