package base;

import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import pages.HomePage;

@Slf4j
abstract public class AbstractBaseTest {

    protected static WebDriver driver;

    public static final String BASE_URL = "https://www.vans.co.uk/";

    @BeforeMethod
    public void openBrowser() {
        driver = new ChromeDriver();
        driver.get(BASE_URL);
        driver.manage().window().maximize();
        HomePage homePage = new HomePage(driver);
        homePage.clickAllowAllCookie();
    }

    @AfterMethod
    public void closeBrowser() {
        driver.quit();
    }

    public static void sleep(int seconds) {
        try {
            Thread.sleep(seconds * 1000L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void openUrl(String url) {
        driver.navigate().to(url);
    }
}