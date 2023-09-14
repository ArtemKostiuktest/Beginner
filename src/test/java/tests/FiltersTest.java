package tests;

import base.AbstractBaseTest;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;
import pages.BrowseProductsPage;
import pages.HomePage;
import pages.ProductPage;

import java.util.ArrayList;
import java.util.List;

import static org.testng.Assert.assertFalse;

public class FiltersTest extends AbstractBaseTest {

    private final String SHOES_SIZE = "2.5";
    private final String SIZE_ACCESSIBILITY_TARGET = "Out of Stock";
    private List<String> listOfAccessibility = new ArrayList<>();
    private List<WebElement> listOfProductsElement = new ArrayList<>();

    @Test(description = "Filtering products by size")
    public void sizeFilterTest() {
        var homePage = new HomePage(driver);
        var browseProductsPage = new BrowseProductsPage(driver);

        homePage.openAllMenShoes();

        browseProductsPage.selectSizeInFilter(SHOES_SIZE);
        browseProductsPage.skipLoading();
        listOfProductsElement = browseProductsPage.getAllProductNamesElements();
        checkingEachProductForSizeAvailability(listOfProductsElement);

        for (String result : listOfAccessibility) {
            assertFalse(result.contains(SIZE_ACCESSIBILITY_TARGET));
        }
    }

    private void checkingEachProductForSizeAvailability(List<WebElement> elements) {
        for (int i = 1; i <= elements.size(); i++) {
            ProductPage productPage = new ProductPage(driver);
            BrowseProductsPage browseProductsPage = new BrowseProductsPage(driver);

            browseProductsPage.selectSpecificProduct(i);
            productPage.SelectDropdownSizeButton();
            listOfAccessibility.add(productPage.getValueOfSizeAccessibility(SHOES_SIZE));
            driver.navigate().back();
        }
    }
}
