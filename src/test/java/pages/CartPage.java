package pages;

import base.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

import static java.lang.String.format;

public class CartPage extends BasePage {

    protected final String NAME_SHOES_FIELD_CART = "//h3[@class='item-detail-info-name item-name-js']";
    protected final String PRICE_SHOES_FIELD_CART = "//dd[contains(text(),'%s')]";
    protected final String SIZE_SHOES_FIELD_CART = "//dd[contains(text(),'%s')]";
    protected final String CHECKOUT_BUTTON = "//a[@class='button primary btn-checkout-js ']";

    public String selectPriceShoesFieldCart(String price) {
        return format(PRICE_SHOES_FIELD_CART, price);
    }

    public String selectSizeShoesFieldCart(String cart_size){
        return format(SIZE_SHOES_FIELD_CART,cart_size);
    }

    public CartPage(WebDriver driver) {
        super(driver);
    }

    public List<String> getInfoAboutProductOnCart(String cart_size,String price) {

        List<String> infoAboutShoesOnCart = new ArrayList<>();

        WebElement shoesNameOnCart = waitUntilVisibilityOfElement(NAME_SHOES_FIELD_CART);
        infoAboutShoesOnCart.add(shoesNameOnCart.getText());

        WebElement shoesPriceOnCart = waitUntilVisibilityOfElement(selectPriceShoesFieldCart(price));
        infoAboutShoesOnCart.add(shoesPriceOnCart.getText());

        WebElement shoesSizeOnCart = waitUntilVisibilityOfElement(selectSizeShoesFieldCart(cart_size));
        infoAboutShoesOnCart.add(shoesSizeOnCart.getText());

        return infoAboutShoesOnCart;
    }

    public String getProductTitle(){
        return waitUntilElementIsPresent(NAME_SHOES_FIELD_CART).getText().toLowerCase();
    }
    public void checkoutSecurely(){
        waitUntilElementToBeClickable(CHECKOUT_BUTTON).click();
    }
}