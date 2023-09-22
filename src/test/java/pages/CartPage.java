package pages;

import base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

import static java.lang.String.format;

public class CartPage extends BasePage {

    private final String NAME_SHOES_FIELD_CART = "//h3[@class='item-detail-info-name item-name-js']";
    private final String PRICE_SHOES_FIELD_CART = "//dd[contains(text(),'%s')]";
    private final String SIZE_SHOES_FIELD_CART = "//dd[contains(text(),'%s')]";
    private final String REMOVE_ITEM = "//div[@class='checkout-item-detail-edit item-detail-edit']//a[@data-action='delete']";
    private final String CHECK_CART_ITEM = "//span[text()='THERE ARE NO ITEMS IN YOUR CART']";
    private final String AFTER_DELETE_MESSAGE = "THERE ARE NO ITEMS IN YOUR CART";
    private final String EDIT_ITEM = "//div[@class='checkout-item-detail-edit item-detail-edit']//a[@data-open='modal']";
    protected final String EDIT_SIZE_SHOES = "//div[@value='%s']";
    protected final String CHECKOUT_BUTTON = "//a[@class='button primary btn-checkout-js ']";
    protected final String UPDATE_BUTTON = "//button[contains(text(),'%s')]";


    public void editedShoes(String editedProduct){
        waitUntilElementToBeClickable(format(UPDATE_BUTTON,editedProduct)).click();
    }

    public String selectPriceShoesFieldCart(String price) {
        return format(PRICE_SHOES_FIELD_CART, price);
    }

    public String selectSizeShoesFieldCart(String cart_size) {
        return format(SIZE_SHOES_FIELD_CART, cart_size);
    }

    public String selectEditSizeShoesFieldCart(String edit_cart_size) {
        return format(EDIT_SIZE_SHOES, edit_cart_size);
    }

    public void selectEditSize(String size) {
        waitUntilElementToBeClickable(selectEditSizeShoesFieldCart(size)).click();
    }

    public void removeItem() {
        waitUntilElementToBeClickable(REMOVE_ITEM).click();
    }

    public void editItem() {
        waitUntilElementToBeClickable(EDIT_ITEM).click();
    }

    public CartPage(WebDriver driver) {
        super(driver);
    }

    public List<String> getInfoAboutProductOnCart(String cart_size, String price) {

        List<String> infoAboutShoesOnCart = new ArrayList<>();

        WebElement shoesNameOnCart = waitUntilVisibilityOfElement(NAME_SHOES_FIELD_CART);
        infoAboutShoesOnCart.add(shoesNameOnCart.getText());

        WebElement shoesPriceOnCart = waitUntilVisibilityOfElement(selectPriceShoesFieldCart(price));
        infoAboutShoesOnCart.add(shoesPriceOnCart.getText());

        WebElement shoesSizeOnCart = waitUntilVisibilityOfElement(selectSizeShoesFieldCart(cart_size));
        infoAboutShoesOnCart.add(shoesSizeOnCart.getText());

        return infoAboutShoesOnCart;
    }

    public boolean IsThereAreNoItemsInYourCartVisibleTitle() {
        return driver.findElement(By.xpath(CHECK_CART_ITEM))
                .getText().contains(AFTER_DELETE_MESSAGE);
    }

    public String getProductTitle() {
        return waitUntilElementIsPresent(NAME_SHOES_FIELD_CART).getText().toLowerCase();
    }

    public void checkoutSecurely() {
        waitUntilElementToBeClickable(CHECKOUT_BUTTON).click();
    }
}