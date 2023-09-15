package pages;

import base.AbstractBaseTest;
import base.BasePage;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;

public class HomePage extends BasePage {

    protected final String BASE_SEARCH_FIELD = "//input[@name ='searchTerm']";
    protected final String ALLOW_ALL_COOKIE_BUTTON = "//button[@id='onetrust-accept-btn-handler']";
    public HomePage(WebDriver driver) {
        super(driver);
    }

    public void fillBaseSearchField(String searchField) {
        waitUntilElementToBeClickable(BASE_SEARCH_FIELD).sendKeys(searchField + Keys.ENTER);
    }

    public void clickAllowAllCookie() {
        waitUntilElementToBeClickable(ALLOW_ALL_COOKIE_BUTTON).click();
    }
}
