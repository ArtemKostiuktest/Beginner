package pages;

import base.BasePage;
import org.openqa.selenium.WebDriver;

public class GiftCardsPage extends BasePage {

    protected final String BUY_GIFT_CARDS = "//a[@class='gc-hero__cta gc-track']";

    public GiftCardsPage(WebDriver driver) {
        super(driver);
    }

    public void clickButtonBuyGiftCard() {
        waitUntilElementToBeClickable(BUY_GIFT_CARDS).click();
    }
}