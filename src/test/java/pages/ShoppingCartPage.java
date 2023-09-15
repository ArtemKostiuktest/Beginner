package pages;

import base.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

import static base.BaseCartPage.*;
import static pages.HomePage.SHOP_NOW_BUTTON;
import static pages.NamePage.SHOES_PICK;
import static pages.ProductPage.*;

public class ShoppingCartPage extends BasePage {
    public ShoppingCartPage(WebDriver driver) {
        super(driver);
    }

    protected final String PROCEED_TO_CHECKOUT = "(//div[contains(@class, 'mini-cart-actions cart-actions')])[1]";

    public void goToCatelogue() {
        waitUntilElementToBeClickable(SHOP_NOW_BUTTON).click();
    }

    public void selectPage() {
        waitUntilElementToBeClickable(SHOES_PICK).click();

    }

    public void  selectSizeDropdown() {
        waitUntilElementToBeClickable(DROPDOWN_SELECT_SIZE).click();

    }

    public void  selectSize() {
        waitUntilElementToBeClickable(SELECT_EXACT_SIZE).click();
    }

    public void addToCart() {
        waitUntilElementToBeClickable(ADD_TO_CARD).click();
    }

    public void proceedToCheckout() {
        waitUntilElementToBeClickable(PROCEED_TO_CHECKOUT).click();
    }

    public List<String> getInfoAboutProductOnHomePage() {
        List<String> infoAboutShoes = new ArrayList<>();

        WebElement shoesName = waitUntilVisibilityOfElement(PRODUCT_TITLE);
        infoAboutShoes.add(shoesName.getText());

        WebElement shoesPrice = waitUntilVisibilityOfElement(PRODUCT_PRICE);
        infoAboutShoes.add(shoesPrice.getText());

        WebElement shoesSize = waitUntilVisibilityOfElement(SIZE_SHOES_FIELD);
        infoAboutShoes.add(shoesSize.getText());

        return infoAboutShoes;

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
