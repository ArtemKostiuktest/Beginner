package pages;

import base.BasePage;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

public class HomePage extends BasePage {
    public HomePage(WebDriver driver) {
        super(driver);
    }
    static final String BASE_SEARCH_FIELD = "//input[@name ='searchTerm']";
    static final String ALLOW_ALL_COOKIE_BUTTON = "//button[@id='onetrust-accept-btn-handler']";
    public void fillBaseSearchField(String searchField){
        waitUntilElementToBeClickable(BASE_SEARCH_FIELD ).sendKeys(searchField + Keys.ENTER);
    }

    public void clickAllowAllCookie() {
        waitUntilElementToBeClickable(ALLOW_ALL_COOKIE_BUTTON).click();
    }






}
