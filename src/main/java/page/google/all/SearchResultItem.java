package page.google.all;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import page.common.SearchResultBaseItem;

import java.util.regex.Pattern;

public class SearchResultItem extends SearchResultBaseItem {

    public SearchResultItem(WebElement element) {
        super(element);
    }

    /**
     * Return link of search item
     * @return
     */
    public String getLink() {
        var link = webElement.findElement(By.cssSelector("div > a"));
        return link.getAttribute("href");
    }

    /**
     * Return rating for search item
     * @return
     */
    public Double getRating() {
        var ratingSpan = webElement.findElement(By.cssSelector("g-review-stars > span"));
        var ratingText = ratingSpan.getAttribute("aria-label");

        // Оценка: 4,5 из 5, => 4,5
        var matcher = Pattern.compile("[-+]?[0-9(,)]*\\.?[0-9(,)]+").matcher(ratingText);
        matcher.find();

        return Double.parseDouble(matcher.group().replace(",", "."));
    }
}
