package tests;

import java.io.ObjectInputFilter.Config;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;

import Utilities.ConfigReader;
import io.github.bonigarcia.wdm.WebDriverManager;
import pages.BasePage;

public class BaseTest {

    WebDriver driver;
	BasePage basePage;
	ConfigReader configReader;

    @BeforeMethod
	public void setUp() {

		configReader = new ConfigReader();
		String baseUrl = configReader.getProperty("baseURL");
		String region = configReader.getProperty("region");
		String language = configReader.getProperty("language");

		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get(baseUrl);

		basePage = new BasePage(driver);
		basePage.acceptCookiesBanner();
		basePage.selectRegionLanguage(region, language);
		
	}

	@AfterMethod()
	public void tearDown(){
		driver.quit();
	}
}
