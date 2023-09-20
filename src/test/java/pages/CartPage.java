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

    public String selectPriceShoesFieldCart(String price) {
        return format(PRICE_SHOES_FIELD_CART, price);
    }

    public String selectSizeShoesFieldCart(String cart_size){
        return format(SIZE_SHOES_FIELD_CART,cart_size);
    }

    public void removeItem() {
        waitUntilElementToBeClickable(REMOVE_ITEM).click();
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
    public boolean IsThereAreNoItemsInYourCartVisibleTitle(){
        return driver.findElement(By.xpath(CHECK_CART_ITEM))
                .getText().contains(AFTER_DELETE_MESSAGE);
    }

}