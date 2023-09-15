package pages;

import base.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

public class ProductPage extends BasePage {

    protected static final String DROPDOWN_SELECT_SIZE = "//div[@class='product-content-form-size-step-dropdown-container custom-dropdown-container']";
    protected static final String SELECT_EXACT_SIZE = "//div[@value='2.5']";
    protected static final String ADD_TO_CARD = "//button[@data-add-to-cart-text='add to cart']";
    protected static final String PRODUCT_TITLE = "//h1[@class='product-info-js']";
    protected static final String PRODUCT_PRICE = "//div[contains(@class, 'product-price product-price-js')]//span[contains(@class,'product-price-amount-js')]";
    protected static final String SIZE_SHOES_FIELD = "//option[@value = '2.5']";
    protected static final String PROCEED_TO_CHECKOUT = "(//div[contains(@class, 'mini-cart-actions cart-actions')])[1]";

    public ProductPage(WebDriver driver) {
        super(driver);
    }

    public void selectSizeDropdown() {
        waitUntilElementToBeClickable(DROPDOWN_SELECT_SIZE).click();
    }

    public void selectSize() {
        waitUntilElementToBeClickable(SELECT_EXACT_SIZE).click();
    }

    public void addToCart() {
        waitUntilElementToBeClickable(ADD_TO_CARD).click();
    }

    public void proceedToCheckout() {
        waitUntilElementToBeClickable(PROCEED_TO_CHECKOUT).click();
    }

    public List<String> getInfoAboutProductsOnProductPage() {
        List<String> infoAboutShoes = new ArrayList<>();

        WebElement shoesName = waitUntilVisibilityOfElement(PRODUCT_TITLE);
        infoAboutShoes.add(shoesName.getText());

        WebElement shoesPrice = waitUntilVisibilityOfElement(PRODUCT_PRICE);
        infoAboutShoes.add(shoesPrice.getText());

        WebElement shoesSize = waitUntilVisibilityOfElement(SIZE_SHOES_FIELD);
        infoAboutShoes.add(shoesSize.getText());

        return infoAboutShoes;

    }

}
