package pages;

import base.BasePage;
import org.openqa.selenium.WebDriver;

public class HomePage extends BasePage {

    private final String ALLOW_ALL_COOKIE_BUTTON = "//button[@id='onetrust-accept-btn-handler']";
    private final String SHOP_NOW_BUTTON = "//a[contains(@href,'CTA_ShopNow.HP_Hero')]";

    public HomePage(WebDriver driver) {
        super(driver);
    }

    public void clickAllowAllCookie() {
        waitUntilElementToBeClickable(ALLOW_ALL_COOKIE_BUTTON).click();
    }

    public void goToCatalogueShopNow(){
        waitUntilElementToBeClickable(SHOP_NOW_BUTTON).click();
    }
}