package tests;

import base.AbstractBaseTest;
import org.testng.annotations.Test;
import pages.HomePage;

public class ShoppingCartTest extends AbstractBaseTest {

    @Test(description = "Check the adding and removing goods  from the cart")
    public void additionAndDeletingFromCartTest() {
        HomePage homePage = new HomePage(driver);

    }
}

