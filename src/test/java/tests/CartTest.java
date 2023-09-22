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

public class CartTest extends AbstractBaseTest {
    public HomePage homePage;
    public ProductPage productPage;
    public BrowseProductsPage browseProductsPage;
    public CartPage cartPage;

    private static final String SIZE = "2.5";
    private static final String PRICE = "75.00";
    private static final String CART_SIZE = "2.5";
    private static final String EDIT_CART_SIZE = "3";
    private static final String ORDER_ITEM_DISPLAY = "OrderItemDisplay";
    private static final String EDITED_PRODUCT = "Update";

    SoftAssert softAssert;

    List<String> current_price;
    List<String> actual_price;

    public void preconditions() {
        homePage = new HomePage(driver);
        productPage = new ProductPage(driver);
        browseProductsPage = new BrowseProductsPage(driver);
        cartPage = new CartPage(driver);
        softAssert = new SoftAssert();

        homePage.goToCatalogueShopNow();
        browseProductsPage.selectPage();
        productPage.selectSizeDropdown();
        productPage.selectSize(SIZE);
        productPage.addToCart();
    }

    @Test(description = "Add product to cart")
    public void checkAddToCart() {
        preconditions();
        current_price = productPage.getInfoAboutProductsOnProductPage(SIZE);
        productPage.proceedToCheckout();
        actual_price = cartPage.getInfoAboutProductOnCart(CART_SIZE, PRICE);

        softAssert.assertEquals(actual_price, current_price);
        softAssert.assertAll();
    }

    @Test(description = "Delete product from cart")
    public void checkDeleteProductFromCart() {
        preconditions();
        productPage.proceedToCheckout();
        cartPage.removeItem();

        softAssert.assertTrue(driver.getCurrentUrl().contains(ORDER_ITEM_DISPLAY));
        softAssert.assertTrue(cartPage.IsThereAreNoItemsInYourCartVisibleTitle());
        softAssert.assertAll();
    }

    @Test(description = "Edit product in cart")
    public void editProductInCart(){
        preconditions();
        current_price = productPage.getInfoAboutProductsOnProductPage(SIZE);
        productPage.proceedToCheckout();
        cartPage.editItem();
        productPage.selectSizeDropdown();
        cartPage.selectEditSize(EDIT_CART_SIZE);
        cartPage.editedShoes(EDITED_PRODUCT);

        softAssert.assertFalse(current_price.get(0).equals(EDIT_CART_SIZE));
        softAssert.assertAll();
    }
}
