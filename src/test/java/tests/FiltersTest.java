package tests;

import base.AbstractBaseTest;
import org.assertj.core.api.SoftAssertions;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;
import pages.BrowseProductsPage;
import pages.HeaderFragment;
import pages.ProductPage;

import java.util.ArrayList;
import java.util.List;

public class FiltersTest extends AbstractBaseTest {

    private final String SHOES_SIZE = "2.5";
    private final String SIZE_ACCESSIBILITY_TARGET = "Out of Stock";
    private final String SORT_VALUE = "Low to High";
    private final String FILTER_VALUE = "Old Skool";
    private int filterProductValue;
    private List<String> listOfAccessibility = new ArrayList<>();
    private List<Double> listOfProductsPrices = new ArrayList<>();
    private List<String> listOfProductsTitles = new ArrayList<>();
    private List<WebElement> productsOptions = new ArrayList<>();

    @Test(description = "Filtering products by size")
    public void sizeFilterTest() {
        HeaderFragment headerFragment = new HeaderFragment(driver);
        BrowseProductsPage browseProductsPage = new BrowseProductsPage(driver);
        SoftAssertions softAssert = new SoftAssertions();

        headerFragment.openAllMenShoes();
        browseProductsPage.selectSizeInFilter(SHOES_SIZE);
        browseProductsPage.waitLoading();
        productsOptions = browseProductsPage.getAllProductNamesElements();
        checkingEachProductForSizeAvailability(productsOptions);

        for (String result : listOfAccessibility) {
            softAssert.assertThat(result).doesNotContain(SIZE_ACCESSIBILITY_TARGET);
        }
        softAssert.assertAll();
    }

    @Test(description = "Filtering products by price(lower to high)")
    public void priceFilterTest() {
        HeaderFragment headerFragment = new HeaderFragment(driver);
        BrowseProductsPage browseProductsPage = new BrowseProductsPage(driver);
        SoftAssertions softAssert = new SoftAssertions();

        headerFragment.openAllMenShoes();
        browseProductsPage.selectSortByDropdown();
        browseProductsPage.selectSortBy(SORT_VALUE);
        browseProductsPage.waitLoading();
        productsOptions = browseProductsPage.getAllProductPricesElements();
        listOfProductsPrices = browseProductsPage.getPrices();

        for (int i = 1; i < listOfProductsPrices.size(); i++) {
            double currentValue = listOfProductsPrices.get(i);
            double previousValue = listOfProductsPrices.get(i-1);
            softAssert.assertThat(currentValue >= previousValue);
        }
        softAssert.assertAll();
    }

    @Test(description = "Filtering products by Silhouette")
    public void silhouetteFilterTest() {
        HeaderFragment headerFragment = new HeaderFragment(driver);
        BrowseProductsPage browseProductsPage = new BrowseProductsPage(driver);
        SoftAssertions softAssert = new SoftAssertions();

        headerFragment.openAllMenShoes();
        browseProductsPage.selectFilterBy(FILTER_VALUE);
        browseProductsPage.waitLoading();
        listOfProductsTitles = browseProductsPage.getTitlesNames();

        for (String name : listOfProductsTitles) {
            softAssert.assertThat(name).contains(FILTER_VALUE.toLowerCase());
        }
        softAssert.assertAll();
    }

    @Test(description = "Checking the compliance of the quantitative value of the filter")
    public void filterProductValueTest() {
        HeaderFragment headerFragment = new HeaderFragment(driver);
        BrowseProductsPage browseProductsPage = new BrowseProductsPage(driver);
        SoftAssertions softAssert = new SoftAssertions();

        headerFragment.openAllMenShoes();
        filterProductValue = browseProductsPage.getFilterCounter(FILTER_VALUE);
        browseProductsPage.selectFilterBy(FILTER_VALUE);
        browseProductsPage.waitLoading();
        browseProductsPage.loadAll();

        softAssert.assertThat(filterProductValue).isEqualTo(browseProductsPage.getTitlesNames().size());
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
