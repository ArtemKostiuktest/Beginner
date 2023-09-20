package tests;

import base.AbstractBaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.*;

import java.util.List;

public class AddToCartTest extends AbstractBaseTest {

    private static final String SIZE = "2.5";
    private static final String PRICE = "75.00";
    private static final String CART_SIZE = "2.5";

    List<String> current_price;
    List<String> actual_price;

    @Test(description = "Check add to cart")
    public void checkAddToCart() {
        HomePage homePage = new HomePage(driver);
        ProductPage productPage = new ProductPage(driver);
        BrowseProductsPage browseProductsPage = new BrowseProductsPage(driver);
        CartPage cartPage = new CartPage(driver);

        homePage.goToCatalogueShopNow();

        browseProductsPage.selectPage();

        productPage.selectSizeDropdown();
        productPage.selectSize(SIZE);
        productPage.addToCart();

        current_price = productPage.getInfoAboutProductsOnProductPage(SIZE);

        productPage.proceedToCheckout();

        actual_price = cartPage.getInfoAboutProductOnCart(CART_SIZE,PRICE);

        Assert.assertEquals(actual_price, current_price);

    }
}
