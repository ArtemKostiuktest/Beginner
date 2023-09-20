package pages;

import base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static java.lang.String.format;
import static utils.Utils.getElementText;

public class BillingAddressPage extends BasePage {

    protected final String SELECT_UKRAINE_COUNTRY = "//select[@id='country']//option[@value='%s']";
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
    protected final String ACCEPT_TERMS_CHECKBOX = "//input[@id='wp-CheckboxConfirmTermsAndConditions']";
    protected final String PROCEED_TO_PAYMENT = "//a[@class='button primary expand btn-shipping-address-js']";
    protected final String ADDRESS_INFO = "//span[@class ='billing-address-part']";
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

    public List<String> getInfoAboutShippingData() {
        List<String> infoAboutData = new ArrayList<>();
        infoAboutData.add(waitUntilVisibilityOfElement(FIRST_NAME).getText());
        infoAboutData.add(waitUntilVisibilityOfElement(LAST_NAME).getText());
        infoAboutData.add(waitUntilVisibilityOfElement(CONFIRM_ADDRESS).getText());
        infoAboutData.add(waitUntilVisibilityOfElement(CITY_NAME).getText());
        infoAboutData.add(waitUntilVisibilityOfElement(POST_CODE).getText());
        infoAboutData.add(waitUntilVisibilityOfElement(PHONE_NUMBER).getText());
        infoAboutData.add(waitUntilVisibilityOfElement(EMAIL).getText());

        return infoAboutData;
    }

    public List<WebElement> getAllProductNamesElements() {
        return waitPresenceOfElementsLocated(ADDRESS_INFO);
    }

    public List<String> getInfoAboutPaymentData() {
        List<String> infoAboutData = new ArrayList<>();
        boolean isFirstElement = true;

        for (WebElement element : getAllProductNamesElements()) {
            String title = getElementText(element);

            if (isFirstElement) {
                String[] parts = title.split(" ");
                for (String part : parts) {
                    infoAboutData.add(part);
                }
                isFirstElement = false;
            } else {
                infoAboutData.add(title);
            }
        }

        Iterator<String> iterator = infoAboutData.iterator();
        while (iterator.hasNext()) {
            String item = iterator.next();
            if (item.isEmpty()) {
                iterator.remove();
            }
        }

        return infoAboutData;
    }
}

