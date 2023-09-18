package tests;

import base.AbstractBaseTest;
import org.testng.annotations.Test;
import pages.BrowseProductsPage;
import pages.HomePage;
import pages.ProductPage;

public class ShoppingCartTest extends AbstractBaseTest {

    @Test(description = "Check the adding and removing goods  from the cart")
    public void additionAndDeletingFromCartTest() {
        HomePage homePage = new HomePage(driver);
        BrowseProductsPage browseProductsPages = new BrowseProductsPage(driver);
        ProductPage productPage = new ProductPage(driver);

        homePage.openAllMenShoes();
        browseProductsPages.selectSpecificProduct(1);
        productPage.SelectDropdownSizeButton();




    }
}

