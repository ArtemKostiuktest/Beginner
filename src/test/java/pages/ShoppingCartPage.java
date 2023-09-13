package pages;

import base.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

public class ShoppingCartPage extends BasePage {
    public ShoppingCartPage(WebDriver driver) {
        super(driver);
    }

    protected final String SHOP_NOW_BUTTON = "//a[@href='https://www.vans.co.uk/shop/en-gb/vans-gb/knu-skool#banner=WK23.TheKnuStack.CTA_ShopNow.HP_Hero']";

    protected final String SHOES_PICK = "(//figure[@class='product-block-figure'])[1]";

    protected final String DROPDOWN_SELECT_SIZE = "//div[@class='product-content-form-size-step-dropdown-container custom-dropdown-container']";

    protected final String SELECT_EXACT_SIZE = "//div[@value='2.5']";

    protected final String ADD_TO_CARD = "//button[@data-add-to-cart-text='add to cart']";

    protected final String PROCEED_TO_CHECKOUT = "(//div[contains(@class, 'mini-cart-actions cart-actions')])[1]";

    protected final String NAME_SHOES_FIELD = "//h1[@class='product-info-js']";

    protected final String PRICE_SHOES_FIELD = "//div[contains(@class, 'product-price product-price-js')]//span[contains(@class,'product-price-amount-js')]";

    protected final String SIZE_SHOES_FIELD = "//option[@value = '2.5']";

    protected final String NAME_SHOES_FIELD_CARD = "//h3[@class='item-detail-info-name item-name-js']";

    protected final String PRICE_SHOES_FIELD_CARD = "//div[@class='item-detail-total item-detail-total-js small-6 medium-3 large-1 columns ']";

    protected final String SIZE_SHOES_FIELD_CARD = "//dd[contains(text(),'2.5')]";

    public ShoppingCartPage clickShopNowButton() {
        waitUntilElementToBeClickable(SHOP_NOW_BUTTON).click();
        return this;
    }

    public ShoppingCartPage clickOnShoes() {
        waitUntilElementToBeClickable(SHOES_PICK).click();
        return this;
    }

    public ShoppingCartPage clickOnSelectSizeDropdown() {
        waitUntilElementToBeClickable(DROPDOWN_SELECT_SIZE).click();
        return this;
    }

    public ShoppingCartPage clickOnSelectSize() {
        waitUntilElementToBeClickable(SELECT_EXACT_SIZE).click();
        return this;
    }

    public ShoppingCartPage clickOnAddToCard() {
        waitUntilElementToBeClickable(ADD_TO_CARD).click();
        return this;
    }

    public ShoppingCartPage clickOnProceedToCheckout() {
        waitUntilElementToBeClickable(PROCEED_TO_CHECKOUT).click();
        return this;
    }

    public List<String> getInfoAboutProductOnHomePage(){
        List<String> infoAboutShoes = new ArrayList<>();

       WebElement shoesName = waitUntilVisibilityOfElement(NAME_SHOES_FIELD);
       infoAboutShoes.add(shoesName.getText());

       WebElement shoesPrice = waitUntilVisibilityOfElement(PRICE_SHOES_FIELD);
       infoAboutShoes.add(shoesPrice.getText());

       WebElement shoesSize = waitUntilVisibilityOfElement(SIZE_SHOES_FIELD);
       infoAboutShoes.add(shoesSize.getText());

       return infoAboutShoes;

    }

    public List<String> getInfoAboutProductOnCard(){

        List<String> infoAboutShoesOnCard = new ArrayList<>();

        WebElement shoesNameOnCard = waitUntilVisibilityOfElement(NAME_SHOES_FIELD_CARD);
        infoAboutShoesOnCard.add(shoesNameOnCard.getText());

        WebElement shoesPriceOnCard = waitUntilVisibilityOfElement(PRICE_SHOES_FIELD_CARD);
        infoAboutShoesOnCard.add(shoesPriceOnCard.getText());

        WebElement shoesSizeOnCard = waitUntilVisibilityOfElement(SIZE_SHOES_FIELD_CARD);
        infoAboutShoesOnCard.add(shoesSizeOnCard.getText());

        return infoAboutShoesOnCard;
    }
}
