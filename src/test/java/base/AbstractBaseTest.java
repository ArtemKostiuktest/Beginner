package base;

import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

@Slf4j
abstract public class AbstractBaseTest {

    protected static WebDriver driver;
    private ChromeOptions chromeOptions;

    public static final String BASE_URL = "https://www.vans.co.uk/";

    @BeforeMethod
    public void openBrowser() {
        chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--disable-geolocation");
        driver = new ChromeDriver(chromeOptions);
        driver.get(BASE_URL);
        driver.manage().window().maximize();
    }

    @AfterMethod
    public void closeBrowser() {
        log.info("<<<=== Teardown");
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