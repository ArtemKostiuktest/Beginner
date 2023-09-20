package tests;

import base.AbstractBaseTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import pages.BrowseProductsPage;
import pages.CartPage;
import pages.HomePage;
import pages.ProductPage;

import java.util.List;

import static org.testng.Assert.assertEquals;

public class CartTest extends AbstractBaseTest {

    public HomePage homePage;
    public ProductPage productPage;
    public BrowseProductsPage browseProductsPage;
    public CartPage cartPage;

    private static final String SIZE = "2.5";
    private static final String PRICE = "75.00";
    private static final String CART_SIZE = "2.5";
    private static final String ORDER_ITEM_DISPLAY = "OrderItemDisplay";

    List<String> current_price;
    List<String> actual_price;

    @BeforeMethod
    public void setUpClass() {
        homePage = new HomePage(driver);
        productPage = new ProductPage(driver);
        browseProductsPage = new BrowseProductsPage(driver);
        cartPage = new CartPage(driver);

        homePage.goToCatalogueShopNow();
        browseProductsPage.selectPage();
        productPage.selectSizeDropdown();
        productPage.selectSize(SIZE);
        productPage.addToCart();
    }

    @Test(description = "Add product to cart")
    public void checkAddToCart() {
        current_price = productPage.getInfoAboutProductsOnProductPage(SIZE);
        productPage.proceedToCheckout();
        actual_price = cartPage.getInfoAboutProductOnCart(CART_SIZE, PRICE);

        assertEquals(actual_price, current_price);
    }

    @Test(description = "Delete product from cart")
    public void checkDeleteProductFromCart() {
        productPage.proceedToCheckout();
        cartPage.removeItem();

        SoftAssert softAssert = new SoftAssert();
        softAssert.assertTrue(driver.getCurrentUrl().contains(ORDER_ITEM_DISPLAY));
        softAssert.assertTrue(cartPage.IsThereAreNoItemsInYourCartVisibleTitle());
        softAssert.assertAll();
    }
}
