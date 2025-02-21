package tests;
import java.time.Year;

import org.testng.Assert;
import org.testng.annotations.Test;

import pages.BasePage;
import pages.SearchResultsPage;

public class SearchResultsTest extends BaseTest {

	@Test
	public void verifyFirstResultHeaderContains() throws InterruptedException{
		BasePage basePage = new BasePage(driver);
		SearchResultsPage searchResultPage = basePage.search("Ford F-150");
		String firstResultTitle = searchResultPage.getFirstSearchResultTitle();
		Assert.assertTrue(firstResultTitle.contains("Ford F-150"), "First result does not contain 'Ford F-150'");
	}

	@Test
	public void verifyNumberOfResultsAfterFilter() throws InterruptedException{
		BasePage basePage = new BasePage(driver);
		SearchResultsPage searchResultPage = basePage.search("Ford F150");
		int initialNum = searchResultPage.getTotalNumberOfResults();
		String currentYear = String.valueOf(Year.now().getValue());
		searchResultPage.filterByYear("2010", currentYear);
		int filteredNum = searchResultPage.getTotalNumberOfResults();
		Assert.assertTrue(filteredNum < initialNum, "Filtered count should be less than the initial count");
	}
}
