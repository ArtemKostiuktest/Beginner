package pages;

import base.BasePage;
import org.openqa.selenium.WebDriver;
public class SocksPage extends BasePage {
    protected final String FIRST_GOOD = "//*[@id='catalog-results']/div[1]";
    public SocksPage(WebDriver driver) {
        super(driver);
    }

    public void clickSocks() {
        waitUntilElementToBeClickable(FIRST_GOOD).click();
    }
}
