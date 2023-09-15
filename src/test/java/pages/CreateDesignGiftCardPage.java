package pages;

import base.BasePage;
import org.openqa.selenium.WebDriver;

public class CreateDesignGiftCardPage extends BasePage {

    public CreateDesignGiftCardPage(WebDriver driver) {
        super(driver);
    }

    protected final String PRICE_CARD_100 = "//button[@data-value='100']";
    protected final String SENDER_NAME = "//input[@name='senderName']";
    protected final String SENDER_EMAIL = "//input[@name='senderEmail']";
    protected final String RECIPIENT_NANE = "//input[@name='recipientName']";
    protected final String RECIPIENT_EMAIL = "//input[@name='recipientEmail']";
    protected final String DESIGN_CARD = "//button[@class='gift-card-design attr-box selected']";
    protected final String MESSAGE = "//textarea[@name='message']";
    protected final String CHECK_BOX = "//input[@name='checkbox']";
    protected final String BUY_NOW_BUTTON = "//button[@class='gift-card-pdp-submit button expanded']";
    public static int price;

    public Integer getPrice() {
        String priceString = waitUntilVisibilityOfElementLocated(PRICE_CARD_100).getText().replaceAll("[^0-9]+", "");
        price = Integer.parseInt(priceString);
        return price;
    }

    public void chooseDesignCard() {
        waitUntilVisibilityOfElementLocated(DESIGN_CARD).click();
    }

    public void choosePriceCard() {
        waitUntilVisibilityOfElement(PRICE_CARD_100).click();
    }

    public void setSenderName(String senderName) {
        waitUntilElementToBeClickable(SENDER_NAME).sendKeys(senderName);
    }

    public void setSenderEmail(String senderEmail) {
        waitUntilElementToBeClickable(SENDER_EMAIL).sendKeys(senderEmail);
    }

    public void setRecipientName(String recipientName) {
        waitUntilElementToBeClickable(RECIPIENT_NANE).sendKeys(recipientName);
    }

    public void setRecipientEmail(String recipientEmail) {
        waitUntilElementToBeClickable(RECIPIENT_EMAIL).sendKeys(recipientEmail);
    }

    public void setMassage(String massage) {
        waitUntilElementToBeClickable(MESSAGE).sendKeys(massage);
    }

    public void clickCheckBox() {
        waitUntilVisibilityOfElementLocated(CHECK_BOX).click();
    }

    public void clickBuyNowButton() {
        waitUntilVisibilityOfElementLocated(BUY_NOW_BUTTON).click();
    }
}