package page.google.all;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import page.common.PageBase;
import page.google.SearchToolbar;

import javax.naming.directory.SearchResult;
import java.util.List;
import java.util.stream.Collectors;

public class SearchResultPage extends PageBase {
    @FindBy(how = How.CSS, using = "div#foot td:last-of-type")
    private WebElement nextButton;

    public SearchToolbar searchToolbar() {
        return new SearchToolbar(driver);
    }

    public SearchResultPage(WebDriver driver) {
        super(driver);
    }

    public <T extends SearchResult> List<SearchResultItem> getSearchResults() {
        return driver.findElements(By.cssSelector("div.rc"))
                .stream()
                .map(item -> new SearchResultItem(item))
                .collect(Collectors.toList());
    }

    public void gotToNextPage() {
        nextButton.click();
    }
}
