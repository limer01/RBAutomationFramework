package pages;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BasePage {

    //Search Bar
    private By searchBar = By.xpath("//input[1]");
    private By searchButton = By.xpath("//button[@data-testid='search button']");
    //Region Pop up
    private By regionPopup = By.xpath("//h1[text()='Confirm your language and region']");
    private By closeX = By.xpath("//*[@data-testid='CloseIcon']");
    //Cookies Banner
    private By cookiesBanner = By.xpath("//div[@id='truste-consent-content']");
    private By cookiesAcceptButton = By.xpath("//button[@id='truste-consent-button']");
        
    private WebDriver driver;
    private WebDriverWait wait;

    public BasePage(WebDriver driver){
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    protected WebElement find(By locator) {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    protected void click(By locator){
        find(locator).click();
    }

    public SearchResultsPage search(String searchQuery){
         find(searchBar).sendKeys(searchQuery);
         find(searchButton).click();

         return new SearchResultsPage(driver);
    }

    public void selectRegion(By locator){
        find(locator).click();
    }

    public void selectRegionLanguage(String region, String language) {
        if (isElementPresent(regionPopup)) {
            By regionOption = By.xpath("//div[@data-testid='"+region+"']//span[text()='"+language+"']");
            click(regionOption);
        }
    }

    public void acceptCookiesBanner() {
        if (isElementPresent(cookiesBanner)) {
            click(cookiesAcceptButton);
        }
    }

    private boolean isElementPresent(By locator) {
        try {
            driver.findElement(locator);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
