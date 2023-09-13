package pages;

import base.BasePage;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import java.util.ArrayList;
import java.util.List;

public class HomePage extends BasePage {
    public HomePage(WebDriver driver) {
        super(driver);
    }

    static final String WOMEN_BUTTON = "//a[@data-navigation='women']";
    static final String MEN_BUTTON = "//a[@data-navigation='men']";
    static final String MEN_SHOES_VIEW_ALL = "//a[@data-navigation='MEN-SHOES:MEN-SHOES:men']";
    static final String ALLOW_ALL_COOKIE_BUTTON = "//button[@id='onetrust-accept-btn-handler']";

    public WebElement menCategoryElement(){
        return waitUntilVisibilityOfElement(MEN_BUTTON);
    }
    public void selectViewAllShoes(){
        waitUntilElementToBeClickable(MEN_SHOES_VIEW_ALL).click();
    }
    public void openAllMenShoes() {
        Actions action = new Actions(driver);
        action.moveToElement(menCategoryElement()).perform();
        selectViewAllShoes();
    }

    public void clickAllowAllCookie() {
        waitUntilElementToBeClickable(ALLOW_ALL_COOKIE_BUTTON).click();
    }
}