package search;

import base.InitTest;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import page.google.GoogleStartPage;
import page.google.all.SearchResultPage;
import page.google.play.GooglePlayPage;
import page.wikipedia.WikiPage;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Test on Google search for IVI
 */
public class SearchResultsTests extends InitTest {
    private final Logger LOG = LogManager.getLogger(SearchResultsTests.class);

    /**
     * Open google page
     * Type IVI
     * Go over a few pages
     * Find a links on play.google.com
     * Validate the rating from google search result and on play market
     */
    @Test
    public void searchLinkForGooglePlay() {
        var searchPage = goToIviSearchPage();

        var googlePlayLinksWithRating = new HashMap<String, Double>();
        // iterate over 5 search pages
        for (var index = 0; index < 5; index++) {
            searchPage.getSearchResults().stream()
                    .filter(item -> item.getLink().toLowerCase().contains("play.google.com"))
                    .forEach(item -> googlePlayLinksWithRating.put(item.getLink(), item.getRating()));
            searchPage.gotToNextPage();
        }

        // validation part
        var softAssert = new SoftAssert();
        for (var result : googlePlayLinksWithRating.entrySet()) {
            searchPage.getDriver().get(result.getKey());
            var gPlayPage = new GooglePlayPage(searchPage.getDriver());
            var gPlayRating = gPlayPage.getRating();

            softAssert.assertEquals(result.getValue(), gPlayRating, "Rating on google search and google play pages should be the same");
        }

        searchPage.getDriver().close();
        softAssert.assertAll();
    }

    /**
     * Open google page
     * Type IVI
     * Go over a few pages
     * Validate, that wikipedia has a links on IVI domain
     */
    @Test
    public void searchLinkOnWikipedia() {
        var searchPage = goToIviSearchPage();

        var wikipediaLinks = new ArrayList<String>();
        // iterate over 5 search pages
        for (var index = 0; index < 5; index++) {
            searchPage.getSearchResults().stream()
                    .filter(item -> item.getLink().toLowerCase().contains("wikipedia"))
                    .forEach(item -> wikipediaLinks.add(item.getLink()));
            searchPage.gotToNextPage();
        }

        // validation part
        var softAssert = new SoftAssert();

        for (var link : wikipediaLinks) {
            searchPage.getDriver().get(link);
            var wikiPage = new WikiPage(searchPage.getDriver());
            var externalLinks = wikiPage.findExternalLinksOnDomain("ivi.ru");

            softAssert.assertTrue(externalLinks.size() > 0,
                    String.format("Wikipedia page '%s' should contain at least 1 link to IVI domain", link));
        }

        searchPage.getDriver().close();
        softAssert.assertAll();
    }

    private SearchResultPage goToIviSearchPage() {
        var driver = getWebDriverInstance();
        var googlePage = new GoogleStartPage(driver);
        return googlePage.getSearchBox().typeText("ivi");
    }
}
