package page.google;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import page.common.PageBase;
import page.google.all.SearchResultPage;

public class SearchBox extends PageBase {
    @FindBy(how = How.CSS, using = "input[title]")
    private WebElement searchBox;

    public SearchBox(WebDriver driver) {
        super(driver);
    }

    public SearchResultPage typeText(String text) {
        searchBox.sendKeys(text);
        searchBox.submit();

        return new SearchResultPage(driver);
    }
}
