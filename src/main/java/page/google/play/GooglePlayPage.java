package page.google.play;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import page.common.PageBase;

public class GooglePlayPage extends PageBase {
    @FindBy(how = How.CSS, using = "c-wiz[jsdata='deferred-c1'] > div > div")
    private WebElement ratingWebElement;

    public Double getRating() {
        var ratingText = ratingWebElement.getText().replace(",", ".");
        return Double.parseDouble(ratingText);
    }

    public GooglePlayPage(WebDriver driver) {
        super(driver);
    }
}
