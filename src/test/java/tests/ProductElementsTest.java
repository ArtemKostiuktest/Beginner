package tests;

import base.AbstractBaseTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import pages.HeaderFragment;
import pages.SocksDescriptionPage;
import pages.SocksPage;

public class ProductElementsTest extends AbstractBaseTest {

    public HeaderFragment headerFragment;
    public SocksPage socksPage;
    public SocksDescriptionPage socksDescriptionPage;
    private static final String SOCKS = "socks";
    SoftAssert softAssert;


    public void preconditions() {
        headerFragment = new HeaderFragment(driver);
        socksPage = new SocksPage(driver);
        socksDescriptionPage = new SocksDescriptionPage(driver);
        softAssert = new SoftAssert();

    }

    @Test
    public void searchGoods()
    {
        preconditions();
        headerFragment.fillBaseSearchField(SOCKS);

        socksPage.clickSocks();

        softAssert.assertTrue(socksDescriptionPage.isNameOfGoodVisible(), "The element is not displayed in SocksDescriptionPage");
        softAssert.assertTrue(socksDescriptionPage.isPriceOfGoodVisible(), "The element is not displayed in SocksDescriptionPage");
        softAssert.assertTrue(socksDescriptionPage.isColorOfGoodVisible(), "The element is not displayed in SocksDescriptionPage");
        softAssert.assertAll();

    }
}
