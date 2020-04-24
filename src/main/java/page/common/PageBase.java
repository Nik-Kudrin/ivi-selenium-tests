package page.common;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

/**
 * Base page for other entities
 */
public class PageBase {
    protected final WebDriver driver;

    public WebDriver getDriver() {
        return driver;
    }

    public PageBase(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }
}
