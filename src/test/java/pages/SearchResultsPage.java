package pages;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class SearchResultsPage extends BasePage{

        //Define Locators
        private By totalSearchResultsText = By.xpath("//h2[@data-testid='non-cat-header']");
        private By firstSearchResultTitle = By.xpath("//a[@class='MuiTypography-root MuiTypography-inherit MuiLink-root MuiLink-underlineHover muiltr-h8u5m'][1]");
        private By yearFilterDropdown = By.xpath("//div[@id='manufactureYearRange-header']");
        private By startYearFilter = By.xpath("//input[@id='manufactureYearRange_min']");
        private By endYearFilter = By.xpath("//input[@id='manufactureYearRange_max']");
        private By yearFilterChip = By.xpath("//div[@id='manufactureYearRange']");

        public SearchResultsPage(WebDriver driver) {
                super(driver);
        }

        public String getFirstSearchResultTitle(){
                return find(firstSearchResultTitle).getText();
        }

        public String getTotalSearchResultsText(){
                return find(totalSearchResultsText).getText();
        }

        public int getTotalNumberOfResults(){
                String totalNumberOfResultsText = getTotalSearchResultsText();
                Pattern pattern = Pattern.compile("of\\s(\\d+)\\sresults");
                Matcher matcher = pattern.matcher(totalNumberOfResultsText);
                if (matcher.find()) {
                        return Integer.parseInt(matcher.group(1));
                } else {
                        throw new RuntimeException("Could not extract the total number of results.");
                }
        }

        public void filterByYear(String startYear, String endYear) throws InterruptedException {
                click(yearFilterDropdown);
                for (int i = 0; i < startYear.length(); i++) {
		        find(startYearFilter).sendKeys(Keys.BACK_SPACE);
		}
                find(startYearFilter).sendKeys(startYear);

                for (int i = 0; i < endYear.length(); i++) {
		        find(endYearFilter).sendKeys(Keys.BACK_SPACE);
		}
                find(endYearFilter).sendKeys(endYear);
                find(endYearFilter).sendKeys(Keys.ENTER);
                find(yearFilterChip);
        }
}
