package tests;

import base.AbstractBaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.HomePage;
import pages.ShoppingCartPage;

import java.util.List;

public class AddToCartTest extends AbstractBaseTest {
    List<String> current_price;
    List<String> actual_price;

    @Test
    public void checkAddToCart() {
        ShoppingCartPage shoppingCartPage = new ShoppingCartPage(driver);

        HomePage homePage = new HomePage(driver);

        homePage
                .clickCookie();
        shoppingCartPage
                .clickShopNowButton()
                .clickOnShoes()
                .clickOnSelectSizeDropdown()
                .clickOnSelectSize()
                .clickOnAddToCard();
        current_price = shoppingCartPage.getInfoAboutProductOnHomePage();
        shoppingCartPage
                .clickOnProceedToCheckout();
        actual_price = shoppingCartPage.getInfoAboutProductOnCard();

        Assert.assertEquals(actual_price, current_price);

    }
}
