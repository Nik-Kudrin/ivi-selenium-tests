package picturesearch;

import base.InitTest;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import page.google.GoogleStartPage;

import java.io.UnsupportedEncodingException;
import java.util.stream.Collectors;

/**
 * Test on google search for IVI images
 */
public class IviImagesInGoogle extends InitTest {
    private final Logger LOG = LogManager.getLogger(IviImagesInGoogle.class);
    private final String IVI_DOMAIN = "https://www.ivi.ru";

    @DataProvider(name = "minLinksCount")
    public static Object[][] minLinksCount() {
        return new Object[][]{{3}};
    }

    /**
     * Open google page
     * Type ivi
     * Go to image section
     * Validate, that count of links on ivi domain not less than specified
     */
    @Test(dataProvider = "minLinksCount")
    public void searchIviPicturesOnGoogle(int minLinksCount) throws UnsupportedEncodingException {
        var searchResults = new GoogleStartPage(getWebDriverInstance()).getSearchBox().typeText("ivi");
        var imagesPage = searchResults.searchToolbar().clickImageSearchButton();
        var imagesPageSearchResults = imagesPage.getSearchResults();

        var picturesLinks = imagesPageSearchResults.stream()
                .map(item -> item.getWebElement().getAttribute("href"))
                .filter(item -> item != null && item.startsWith(IVI_DOMAIN))
                .collect(Collectors.toList());

        LOG.info(String.format("Found images links " + picturesLinks.stream()
                .reduce("", (first, second) -> first + System.lineSeparator() + second)));

        imagesPage.getDriver().close();

        Assert.assertTrue(picturesLinks.size() > minLinksCount,
                String.format("Found less then %s links (actually - %s) back to ivi from google image search page", minLinksCount, picturesLinks.size()));
    }
}
