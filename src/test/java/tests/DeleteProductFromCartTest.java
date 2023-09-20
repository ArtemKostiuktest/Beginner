package tests;

import base.AbstractBaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.BrowseProductsPage;
import pages.CartPage;
import pages.HomePage;
import pages.ProductPage;

import static tests.AddToCartTest.SIZE;

public class DeleteProductFromCartTest extends AbstractBaseTest {

    @Test
    public void checkDeleteProductFromCart() {

        HomePage homePage = new HomePage(driver);
        BrowseProductsPage browseProductsPage = new BrowseProductsPage(driver);
        ProductPage productPage = new ProductPage(driver);
        CartPage cartPage = new CartPage(driver);

        homePage.goToCatalogueShopNow();

        browseProductsPage.selectPage();

        productPage.selectSizeDropdown();
        productPage.selectSize(SIZE);
        productPage.addToCart();
        productPage.proceedToCheckout();

        cartPage.removeItem();

        Assert.assertTrue(driver.getCurrentUrl().contains("OrderItemDisplay"));
        Assert.assertTrue(cartPage.IsThereAreNoItemsInYourCartVisibleTitle());

    }
}
