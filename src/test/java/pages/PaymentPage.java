package pages;

import base.BasePage;
import org.openqa.selenium.WebDriver;

import static utils.Utils.switchToIframe;

public class PaymentPage extends BasePage {
    private final String BILLING_INFO = "//div[@class='billing-address-display']";
    private final String MONTH_DATA_CARD = "//input[@class='textbox expiry expiry-month']";
    private final String EXPIRY_YEAR_CARD = "//input[@class='textbox expiry expiry-year']";
    private final String SECURITY_CODE_BUTTON = "//input[@class='textbox pin security-code']";
    private final String CARD_HOLDER_NAME = "//input[@class='textbox cardholder-name']";
    private final String MAKE_PAYMENT_BUTTON = "//input[@class='button btn-make-payment']";
    private final String CART_NUMBER = "//input[@id='cardNumber']";
    private final String IFRAME = "//iframe[@title='Payment Pages']";

    public PaymentPage(WebDriver driver) {
        super(driver);
    }

    public String getAllBillingInfo() {
        return waitUntilVisibilityOfElementLocated(BILLING_INFO).getText();
    }

    public void enterCartForm(String cartNumber, String cardMonthBut, String cardYearBut, String securityCode, String nameCardHolder) {
        switchToIframe(driver, IFRAME);
        waitUntilElementIsPresent(CART_NUMBER).sendKeys(cartNumber);
        waitUntilElementIsPresent(MONTH_DATA_CARD).sendKeys(cardMonthBut);
        waitUntilElementIsPresent(EXPIRY_YEAR_CARD).sendKeys(cardYearBut);
        waitUntilElementIsPresent(SECURITY_CODE_BUTTON).sendKeys(securityCode);
        waitUntilElementIsPresent(CARD_HOLDER_NAME).sendKeys(nameCardHolder);
    }

    public boolean isMakePaymentEnabled() {
        return isElementEnabled(MAKE_PAYMENT_BUTTON);
    }
}
