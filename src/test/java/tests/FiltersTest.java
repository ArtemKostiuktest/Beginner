package tests;

import base.AbstractBaseTest;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.HomePage;
import pages.BrowseProductsPage;
import pages.ProductPage;

import java.util.ArrayList;
import java.util.List;

public class FiltersTest extends AbstractBaseTest {

    private final String SHOES_SIZE = "2.5";
    private final String SIZE_ACCESSIBILITY_TARGET = "Out of Stock";
    private List<String> listOfAccessibility = new ArrayList<>();
    private List<WebElement> listOfProductsElement = new ArrayList<>();

    @Test(description = "Filtering products by size")
    public void sizeFilterTest() {
        HomePage homePage = new HomePage(driver);
        BrowseProductsPage browseProductsPage = new BrowseProductsPage(driver);

        homePage.openAllMenShoes();

        browseProductsPage.selectSizeInFilter(SHOES_SIZE);
        browseProductsPage.skipLoading();
        listOfProductsElement = browseProductsPage.getAllProductNamesElements();
        checkingEachProductForSizeAvailability(listOfProductsElement);

        for (String result : listOfAccessibility) {
            Assert.assertFalse(result.contains(SIZE_ACCESSIBILITY_TARGET));
        }
    }

    public void checkingEachProductForSizeAvailability(List<WebElement> elements) {
        for (int i = 1; i <= elements.size(); i++) {
            ProductPage productPage = new ProductPage(driver);
            BrowseProductsPage browseProductsPage = new BrowseProductsPage(driver);

            browseProductsPage.selectSpecificProduct(i);
            productPage.clickSelectSizeButton();
            listOfAccessibility.add(productPage.getValueOfSizeAccessibility(SHOES_SIZE));
            driver.navigate().back();
        }
    }
}
