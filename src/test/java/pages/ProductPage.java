package pages;

import base.BasePage;
import org.openqa.selenium.WebDriver;

import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

import static java.lang.String.format;

public class ProductPage extends BasePage {

    protected final String DROPDOWN_SELECT_SIZE = "//div[@class='product-content-form-size-step-dropdown-container custom-dropdown-container']";
    protected final String SELECT_EXACT_SIZE = "//div[@value='%s']";
    protected final String ADD_TO_CARD = "//button[@data-add-to-cart-text='add to cart']";
    protected final String PRODUCT_TITLE = "//h1[@class='product-info-js']";
    protected final String PRODUCT_PRICE = "//div[contains(@class, 'content')]//span[contains(@class,'of')]";
    protected final String SIZE_SHOES_FIELD = "//option[@value = '%s']";
    protected final String PROCEED_TO_CHECKOUT = "//div[contains(@class,'slide')]//a[contains(text(), 'Proceed')]";
    protected final String SELECT_SIZE_BUTTON = "//div[@class='product-content-form-size-step-dropdown-container custom-dropdown-container']";

    public ProductPage(WebDriver driver) {
        super(driver);
    }

    public void SelectDropdownSizeButton() {
        waitUntilElementToBeClickable(SELECT_SIZE_BUTTON).click();
    }

    public String getValueOfSizeAccessibility(String size) {
        return waitUntilElementToBeClickable(valueOfSizeAccessibility(size)).getText();
    }

    private String valueOfSizeAccessibility(String size) {
        return "//div[@value='" + size + "']";
    }
    public String selectExactSize(String size) {
        return format(SELECT_EXACT_SIZE, size);
    }

    public String selectShoesSize(String size) {
        return format(SIZE_SHOES_FIELD, size);
    }

    public void selectSizeDropdown() {
        waitUntilElementToBeClickable(DROPDOWN_SELECT_SIZE).click();
    }

    public void selectSize(String size) {
        waitUntilElementToBeClickable(selectExactSize(size)).click();
    }

    public void addToCart() {
        waitUntilElementToBeClickable(ADD_TO_CARD).click();
    }

    public void proceedToCheckout() {
        waitUntilElementToBeClickable(PROCEED_TO_CHECKOUT).click();
    }

    public List<String> getInfoAboutProductsOnProductPage(String size) {
        List<String> infoAboutShoes = new ArrayList<>();

        WebElement shoesName = waitUntilVisibilityOfElement(PRODUCT_TITLE);
        infoAboutShoes.add(shoesName.getText());

        WebElement shoesPrice = waitUntilVisibilityOfElement(PRODUCT_PRICE);
        infoAboutShoes.add(shoesPrice.getText());

        WebElement shoesSize = waitUntilVisibilityOfElement(selectShoesSize(size));
        infoAboutShoes.add(shoesSize.getText());

        return infoAboutShoes;
    }
}
