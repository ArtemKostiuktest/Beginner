package pages;

import base.BasePage;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

public class HomePage extends BasePage {
    public HomePage(WebDriver driver) {
        super(driver);
    }

    private final String BASE_SEARCH_FIELD = "//input[@name ='searchTerm']";
    private final String MEN_BUTTON = "//a[@data-navigation='men']";
    private final String MEN_SHOES_VIEW_ALL = "//a[@data-navigation='MEN-SHOES:MEN-SHOES:men']";
    private final String ALLOW_ALL_COOKIE_BUTTON = "//button[@id='onetrust-accept-btn-handler']";

    public void fillBaseSearchField(String searchField) {
        waitUntilElementToBeClickable(BASE_SEARCH_FIELD).sendKeys(searchField + Keys.ENTER);
    }

    public HomePage openAllMenShoes() {
        Actions action = new Actions(driver);
        action.moveToElement(menCategoryElement()).perform();
        selectViewAllMenShoes();

        return this;
    }

    private WebElement menCategoryElement(){
        return waitUntilVisibilityOfElementLocated(MEN_BUTTON);
    }

    private void selectViewAllMenShoes(){
        waitUntilElementToBeClickable(MEN_SHOES_VIEW_ALL).click();
    }

    public void clickAllowAllCookie() {
        waitUntilElementToBeClickable(ALLOW_ALL_COOKIE_BUTTON).click();
    }
}