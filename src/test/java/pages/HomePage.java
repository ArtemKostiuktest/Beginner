package pages;

import base.BasePage;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;

public class HomePage extends BasePage {

    public HomePage(WebDriver driver) {super(driver);}

    protected final String SHOP_NOW_BUTTON = "//a[@href='https://www.vans.co.uk/shop/en-gb/vans-gb/knu-skool#banner=WK23.TheKnuStack.CTA_ShopNow.HP_Hero']";
    protected final String BASE_SEARCH_FIELD = "//input[@name ='searchTerm']";
    protected final String ALLOW_ALL_COOKIE_BUTTON = "//button[@id='onetrust-accept-btn-handler']";

    public void fillBaseSearchField(String searchField) {
        waitUntilElementToBeClickable(BASE_SEARCH_FIELD).sendKeys(searchField + Keys.ENTER);
    }

    public void goToCatalogueShopNow() {
        waitUntilElementToBeClickable(SHOP_NOW_BUTTON).click();
    }

    public void clickAllowAllCookie() {
        waitUntilElementToBeClickable(ALLOW_ALL_COOKIE_BUTTON).click();
    }
}