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
    private List<WebElement> listOfProductsElement = new ArrayList<>();

    @Test(description = "Filtering products by size")
    public void sizeFilterTest() {
        HomePage homePage = new HomePage(driver);
        BrowseProductsPage browseProductsPage = new BrowseProductsPage(driver);
        SoftAssertions softAssert = new SoftAssertions();

        homePage.openAllMenShoes();
        browseProductsPage.selectSizeInFilter(SHOES_SIZE);
        browseProductsPage.waitLoading();
        listOfProductsElement = browseProductsPage.getAllProductNamesElements();
        checkingEachProductForSizeAvailability(listOfProductsElement);

        for (String result : listOfAccessibility) {
            softAssert.assertThat(result).doesNotContain(SIZE_ACCESSIBILITY_TARGET);
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
            navigateBack();
            try {
                browseProductsPage.waitLoading();
            } catch (TimeoutException e) {
                System.out.println("Find Exception: Timeout exception");
            }
        }
    }
}
