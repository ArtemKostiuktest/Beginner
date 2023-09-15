package pages;

import base.BasePage;
import org.openqa.selenium.WebDriver;

public class BillingAddressPage extends BasePage {

    public BillingAddressPage(WebDriver driver) {
        super(driver);
    }

    public static final String SELECT_UKRAINE_COUNTRY = "//select[@id='country']//option[@value='UA']";
    public static final String FIRST_NAME = "//input[@id='first-name']";
    public static final String LAST_NAME = "//input[@id='last-name']";
    public static final String INSERT_ADDRESS = "//input[@id='address-1']";
    public static final String PHONE_NUMBER = "//input[@id='phone-1']";
    public static final String EMAIL = "//input[@id='email-1']";
    public static final String CONFIRM_ADDRESS = "//a[@id='confirmAddress']";
    public static final String CITY_NAME = "//input[@id='city-name']";
    public static final String POST_CODE = "//input[@id='zipcode']";
    public static final String ACTUAL_PRICE = "//dd[@id='amount-after-giftcards-apply-summary']";
    public static final String ACTUAL_DATA = "//div[@id='wp-address-display']";
    public static int finalPrice;

    public Integer getFinalPrice() {
        String priceString = waitUntilVisibilityOfElementLocated(ACTUAL_PRICE).getText();
        int index = priceString.indexOf('.');
        int start = priceString.indexOf('£');
        String finalPriceString = priceString.substring(start, index).replaceAll("[^0-9]+", "");
        finalPrice = Integer.parseInt(finalPriceString);
        return finalPrice;
    }

    public String getActualData() {
        return waitUntilVisibilityOfElementLocated(ACTUAL_DATA).getText();
    }

    public void selectUkraineCountry() {
        waitUntilVisibilityOfElement(SELECT_UKRAINE_COUNTRY).click();
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
}