package pages;

import base.BasePage;
import org.openqa.selenium.WebDriver;

public class HomePage extends BasePage {

    private final String ALLOW_ALL_COOKIE_BUTTON = "//button[@id='onetrust-accept-btn-handler']";

    public HomePage(WebDriver driver) {
        super(driver);
    }

    public void clickAllowAllCookie() {
        waitUntilElementToBeClickable(ALLOW_ALL_COOKIE_BUTTON).click();
    }
}