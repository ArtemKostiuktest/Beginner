package pages;

import base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import static java.lang.String.format;
import static utils.Utils.switchToIframe;

public class BillingAddressPage extends BasePage {

    private final String SELECT_UKRAINE_COUNTRY = "//select[@id='country']//option[@value='%s']";
    private final String FIRST_NAME = "//input[@id='first-name']";
    private final String LAST_NAME = "//input[@id='last-name']";
    private final String INSERT_ADDRESS = "//input[@id='address-1']";
    private final String PHONE_NUMBER = "//input[@id='phone-1']";
    private final String EMAIL = "//input[@id='email-1']";
    private final String CONFIRM_ADDRESS = "//a[@id='confirmAddress']";
    private final String CITY_NAME = "//input[@id='city-name']";
    private final String POST_CODE = "//input[@id='zipcode']";
    private final String ACTUAL_PRICE = "//dd[@id='amount-after-giftcards-apply-summary']";
    private final String ACTUAL_DATA = "//div[@id='wp-address-display']";
    private final String PAYMENT_IFRAME = "//iframe[@id='wp-cl-WPCARDS-iframe-iframe']";
    private final String CART_NUMBER = "//input[@id='cardNumber']";
    private final String ACCEPT_TERMS_CHECKBOX = "//input[@id='wp-CheckboxConfirmTermsAndConditions']";
    private final String PROCEED_TO_PAYMENT = "//a[@class='button primary expand btn-shipping-address-js']";
    private final String BILLING_INFO = "//div[@class='billing-address-display']";
    private final String MONTH_DATA_CARD = "//input[@class='textbox expiry expiry-month']";
    private final String EXPIRY_YEAR_CARD = "//input[@class='textbox expiry expiry-year']";
    private final String SECURITY_CODE_BUTTON = "//input[@class='textbox pin security-code']";
    private final String CARD_HOLDER_NAME ="//input[@class='textbox cardholder-name']";
    private final String MAKE_PAYMENT_BUTTON = "//input[@class='button btn-make-payment']";
    private final String IFRAME = "//iframe[@title='Payment Pages']";
    public static int finalPrice;

    public BillingAddressPage(WebDriver driver) {
        super(driver);
    }

    public void getFinalPrice() {
        String priceString = waitUntilVisibilityOfElementLocated(ACTUAL_PRICE).getText();
        int index = priceString.indexOf('.');
        int start = priceString.indexOf('£');
        String finalPriceString = priceString.substring(start, index).replaceAll("[^0-9]+", "");
        finalPrice = Integer.parseInt(finalPriceString);
    }

    public String getActualData() {
        WebElement iframe;
        waitUntilVisibilityOfElementLocated(PAYMENT_IFRAME);
        iframe = driver.findElement(By.xpath(PAYMENT_IFRAME));
        driver.switchTo().frame(iframe);
        driver.findElement(By.xpath(CART_NUMBER));
        driver.switchTo().defaultContent();
        return waitUntilVisibilityOfElement(ACTUAL_DATA).getText();
    }

    public void selectUkraineCountry(String country) {
        waitUntilVisibilityOfElement(format(SELECT_UKRAINE_COUNTRY, country)).click();
    }

    public void setFirstName(String firstName) {
        waitUntilVisibilityOfElement(FIRST_NAME).sendKeys(firstName);
    }

    public void setLastName(String lastName) {
        waitUntilVisibilityOfElement(LAST_NAME).sendKeys(lastName);
    }

    public void setAddress(String address) {
        waitUntilElementToBeClickable(INSERT_ADDRESS).sendKeys(address);
    }

    public void setPhoneNumber(String phoneNumber) {
        waitUntilElementToBeClickable(PHONE_NUMBER).sendKeys(phoneNumber);
    }

    public void setEmail(String email) {
        waitUntilElementToBeClickable(EMAIL).sendKeys(email);
    }

    public void clickConfirmAddressButton() {
        waitUntilElementToBeClickable(CONFIRM_ADDRESS).click();
    }

    public void setCityName(String cityName) {
        waitUntilVisibilityOfElement(CITY_NAME).sendKeys(cityName);
    }

    public void setPostCode(String postCode) {
        waitUntilVisibilityOfElement(POST_CODE).sendKeys(postCode);
    }

    public void acceptTerms() {
        waitUntilElementToBeClickable(ACCEPT_TERMS_CHECKBOX).click();
    }

    public void proceedPayment() {
        waitUntilElementToBeClickable(PROCEED_TO_PAYMENT).click();
    }

    public void enterCartForm(String cartNumber,String cardMonthBut, String cardYearBut, String securityCode , String nameCardHolder){
        switchToIframe(driver, IFRAME);
        waitUntilElementIsPresent(CART_NUMBER).sendKeys(cartNumber);
        waitUntilElementIsPresent(MONTH_DATA_CARD).sendKeys(cardMonthBut);
        waitUntilElementIsPresent(EXPIRY_YEAR_CARD).sendKeys(cardYearBut);
        waitUntilElementIsPresent(SECURITY_CODE_BUTTON).sendKeys(securityCode);
        waitUntilElementIsPresent(CARD_HOLDER_NAME).sendKeys(nameCardHolder);
    }

    public boolean isMakePaymentEnabled(){
        return isElementEnabled(MAKE_PAYMENT_BUTTON);
    }

    public String getAllBillingInfo() {
        return waitUntilVisibilityOfElementLocated(BILLING_INFO).getText();
    }


}


