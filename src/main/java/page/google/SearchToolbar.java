package page.google;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import page.common.PageBase;
import page.google.image.SearchImagesResultsPage;

import java.io.UnsupportedEncodingException;

public class SearchToolbar extends PageBase {
    private final Logger log = LogManager.getLogger(SearchToolbar.class);

    @FindBy(how = How.XPATH, using = "//div[@id='hdtb-msb-vis']")
    private WebElement toolbar;

    public SearchToolbar(WebDriver driver) {
        super(driver);
    }

    public SearchImagesResultsPage clickImageSearchButton() throws UnsupportedEncodingException {
        // Magic number - because of very obfuscated/generated styles and disability to find a better locatoruc
        var imageSearchButton = toolbar.findElements(By.tagName("a")).get(2);
        imageSearchButton.click();

        return new SearchImagesResultsPage(driver);
    }
}
