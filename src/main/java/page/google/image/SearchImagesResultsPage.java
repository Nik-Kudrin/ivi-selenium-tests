package page.google.image;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import page.google.all.SearchResultPage;

import java.util.List;
import java.util.stream.Collectors;

public class SearchImagesResultsPage extends SearchResultPage {
    public SearchImagesResultsPage(WebDriver driver) {
        super(driver);
    }

    @Override
    public List<ImageSearchResultItem> getSearchResults() {
        return driver.findElements(By.cssSelector("a[data-ved]"))
                .stream()
                .map(item -> new ImageSearchResultItem(item))
                .collect(Collectors.toList());
    }
}
