package pages;

import base.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

public class CartPage extends BasePage {

    public static final String NAME_SHOES_FIELD_CART = "//h3[@class='item-detail-info-name item-name-js']";
    public static final String PRICE_SHOES_FIELD_CART = "//div[@class='item-detail-total item-detail-total-js small-6 medium-3 large-1 columns ']";
    public static final String SIZE_SHOES_FIELD_CART = "//dd[contains(text(),'2.5')]";

    public CartPage(WebDriver driver) {
        super(driver);
    }

    public List<String> getInfoAboutProductOnCart() {

        List<String> infoAboutShoesOnCart = new ArrayList<>();

        WebElement shoesNameOnCart = waitUntilVisibilityOfElement(NAME_SHOES_FIELD_CART);
        infoAboutShoesOnCart.add(shoesNameOnCart.getText());

        WebElement shoesPriceOnCart = waitUntilVisibilityOfElement(PRICE_SHOES_FIELD_CART);
        infoAboutShoesOnCart.add(shoesPriceOnCart.getText());

        WebElement shoesSizeOnCart = waitUntilVisibilityOfElement(SIZE_SHOES_FIELD_CART);
        infoAboutShoesOnCart.add(shoesSizeOnCart.getText());

        return infoAboutShoesOnCart;
    }
}