package page.wikipedia;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import page.common.PageBase;

import java.util.List;

public class WikiPage extends PageBase {
    public WikiPage(WebDriver driver) {
        super(driver);
    }

    public List<WebElement> findExternalLinksOnDomain(String domain) {
        return driver.findElements(By.xpath(String.format("//a[contains(@href, '%s')]", domain)));
    }
}
