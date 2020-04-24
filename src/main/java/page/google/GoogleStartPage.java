package page.google;

import org.openqa.selenium.WebDriver;
import page.common.PageBase;
import util.Config;

public class GoogleStartPage extends PageBase {
    private final String GOOGLE_URL = Config.Instance().getGoogleUrl().toString();

    public GoogleStartPage(WebDriver driver) {
        super(driver);
        driver.get(GOOGLE_URL);
    }

    public SearchBox getSearchBox() {
        return new SearchBox(driver);
    }
}
