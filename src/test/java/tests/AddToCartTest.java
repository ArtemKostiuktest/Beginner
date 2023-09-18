package tests;

import base.AbstractBaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.BrowseProductsPage;
import pages.CartPage;
import pages.HomePage;
import pages.ProductPage;

import java.util.List;

public class AddToCartTest extends AbstractBaseTest {

    private static final String SIZE = "2.5";

    List<String> current_price;
    List<String> actual_price;

    @Test
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

        actual_price = cartPage.getInfoAboutProductOnCart();

        Assert.assertEquals(actual_price, current_price);

    }
}
