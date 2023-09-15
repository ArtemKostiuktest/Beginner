package pages;

import base.BasePage;
import org.openqa.selenium.WebDriver;

public class HomePage extends BasePage {

    public HomePage(WebDriver driver) {super(driver);}

    public static final String GIFT_CARDS = "//div[contains(@class,'cart-container')]//a[@data-navigation='gift']/span[@class='topnav-utility-item-label']";
    public static final String COOKIE_BUTTON = "//button[@id='onetrust-accept-btn-handler']";

    public void clickGiftCards() {
        waitUntilElementToBeClickable(GIFT_CARDS).click();
    }

    public void clickCookie(){
        waitUntilElementToBeClickable(COOKIE_BUTTON).click();
    }
}