package pages;

import base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class BillingAddressPage extends BasePage {

    public BillingAddressPage(WebDriver driver) {
        super(driver);
    }

    protected final String SELECT_UKRAINE_COUNTRY = "//select[@id='country']//option[@value='UA']";
    protected final String FIRST_NAME = "//input[@id='first-name']";
    protected final String LAST_NAME = "//input[@id='last-name']";
    protected final String INSERT_ADDRESS = "//input[@id='address-1']";
    protected final String PHONE_NUMBER = "//input[@id='phone-1']";
    protected final String EMAIL = "//input[@id='email-1']";
    protected final String CONFIRM_ADDRESS = "//a[@id='confirmAddress']";
    protected final String CITY_NAME = "//input[@id='city-name']";
    protected final String POST_CODE = "//input[@id='zipcode']";
    protected final String ACTUAL_PRICE = "//dd[@id='amount-after-giftcards-apply-summary']";
    protected final String ACTUAL_DATA = "//div[@id='wp-address-display']";
    protected final String PAYMENT_IFRAME = "//iframe[@id='wp-cl-WPCARDS-iframe-iframe']";
    protected final String CART_NUMBER = "//input[@id='cardNumber']";
    public static int finalPrice;
    private WebElement iframe;
    private WebElement input_inside_iframe;

    public Integer getFinalPrice() {
        String priceString = waitUntilVisibilityOfElementLocated(ACTUAL_PRICE).getText();
        int index = priceString.indexOf('.');
        int start = priceString.indexOf('£');
        String finalPriceString = priceString.substring(start, index).replaceAll("[^0-9]+", "");
        finalPrice = Integer.parseInt(finalPriceString);
        return finalPrice;
    }

    public String getActualData() {
        waitUntilVisibilityOfElementLocated(PAYMENT_IFRAME);
        iframe = driver.findElement(By.xpath(PAYMENT_IFRAME));
        driver.switchTo().frame(iframe);
        input_inside_iframe = driver.findElement(By.xpath(CART_NUMBER));
        driver.switchTo().defaultContent();
        return waitUntilVisibilityOfElement(ACTUAL_DATA).getText();
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