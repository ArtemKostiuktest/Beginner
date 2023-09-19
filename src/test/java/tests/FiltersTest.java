package tests;

import base.AbstractBaseTest;
import org.assertj.core.api.SoftAssertions;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;
import pages.BrowseProductsPage;
import pages.HomePage;
import pages.ProductPage;

import java.util.ArrayList;
import java.util.List;

public class FiltersTest extends AbstractBaseTest {

    private final String SHOES_SIZE = "2.5";
    private final String SIZE_ACCESSIBILITY_TARGET = "Out of Stock";
    private List<String> listOfAccessibility = new ArrayList<>();
    private List<Double> listOfProductsPrices = new ArrayList<>();
    private List<WebElement> ProductsOptions = new ArrayList<>();

    @Test(description = "Filtering products by size")
    public void sizeFilterTest() {
        HomePage homePage = new HomePage(driver);
        BrowseProductsPage browseProductsPage = new BrowseProductsPage(driver);
        SoftAssertions softAssert = new SoftAssertions();

        homePage.openAllMenShoes();
        browseProductsPage.selectSizeInFilter(SHOES_SIZE);
        browseProductsPage.waitLoading();
        ProductsOptions = browseProductsPage.getAllProductNamesElements();
        checkingEachProductForSizeAvailability(ProductsOptions);

        for (String result : listOfAccessibility) {
            softAssert.assertThat(result).doesNotContain(SIZE_ACCESSIBILITY_TARGET);
        }
        softAssert.assertAll();
    }

    @Test(description = "Filtering products by price(lower to high)")
    public void priceFilterTest() {
        HomePage homePage = new HomePage(driver);
        BrowseProductsPage browseProductsPage = new BrowseProductsPage(driver);
        SoftAssertions softAssert = new SoftAssertions();

        homePage.openAllMenShoes();
        browseProductsPage.selectSortByDropdown();
        browseProductsPage.sortPricesLowToHigh();
        browseProductsPage.waitLoading();
        ProductsOptions = browseProductsPage.getAllProductPricesElements();
        listOfProductsPrices = browseProductsPage.getPrices();

        for (int i = 1; i < listOfProductsPrices.size(); i++) {
            double currentValue = listOfProductsPrices.get(i);
            double previousValue = listOfProductsPrices.get(i-1);
            softAssert.assertThat(currentValue >= previousValue);
        }
        softAssert.assertAll();
    }

    private void checkingEachProductForSizeAvailability(List<WebElement> elements) {
        for (int i = 1; i <= elements.size(); i++) {
            ProductPage productPage = new ProductPage(driver);
            BrowseProductsPage browseProductsPage = new BrowseProductsPage(driver);

            browseProductsPage.selectSpecificProduct(i);
            productPage.SelectDropdownSizeButton();
            listOfAccessibility.add(productPage.getValueOfSizeAccessibility(SHOES_SIZE));
            navigateGoBack();
            try {
                browseProductsPage.waitLoading();
            } catch (TimeoutException e) {
                System.out.println("Find Exception: Timeout exception");
            }
        }
    }
}
