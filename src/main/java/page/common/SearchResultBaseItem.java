package page.common;

import org.openqa.selenium.WebElement;

public class SearchResultBaseItem {
    protected WebElement webElement;

    public WebElement getWebElement() {
        return webElement;
    }

    public SearchResultBaseItem(WebElement element) {
        this.webElement = element;
    }
}
