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

        homePage.clickAllowAllCookie();
        shoppingCartPage.selectPage();
        shoppingCartPage.goToCatelogue();
        shoppingCartPage.selectSizeDropdown();
        shoppingCartPage.selectSize();
        shoppingCartPage.addToCart();

        current_price = shoppingCartPage.getInfoAboutProductOnHomePage();

        shoppingCartPage.proceedToCheckout();

        actual_price = shoppingCartPage.getInfoAboutProductOnCart();

        Assert.assertEquals(actual_price, current_price);

    }
}
