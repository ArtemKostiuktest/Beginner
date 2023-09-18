package tests;

import base.AbstractBaseTest;
import org.assertj.core.api.SoftAssertions;
import org.testng.annotations.Test;
import pages.BrowseProductsPage;
import pages.HomePage;

import java.util.List;

public class SearchProductTest extends AbstractBaseTest {
    private final String nameOfSearchProduct = "Classic";
    private final String toddlerFilterWord = "Toddler";
    private final String yearRange = "1-4 Years";
    List<String> listOfNames;

@Test(description = "Product search by name")
    public void searchProductByName() {
        HomePage homePage = new HomePage(driver);
        BrowseProductsPage browseProductsPage = new BrowseProductsPage(driver);
        SoftAssertions softAssert = new SoftAssertions();

        homePage.fillBaseSearchField(nameOfSearchProduct);

        listOfNames = browseProductsPage.getTitlesNames();
        for (String name : listOfNames) {
            softAssert.assertThat(name).contains(nameOfSearchProduct);
        }
        softAssert.assertAll();
    }

    private final String nameOfSearchProducts = "Shoes";
    List<String> titleNames;
    @Test(description = "Using filter in search")
    public void searchesProductByName() {
        HomePage homePage = new HomePage(driver);
        BrowseProductsPage namePage = new BrowseProductsPage(driver);
        SoftAssertions soft = new SoftAssertions();

        homePage.fillBaseSearchField(nameOfSearchProducts);
        namePage
                .useToddlerFilter()
                .waitLoading();

        titleNames = namePage.getTitlesNamesOneByOne();
        titleNames.forEach(title ->
                soft.assertThat(title)
                        .as("Запис не містить " + toddlerFilterWord + " та " + yearRange)
                        .contains(toddlerFilterWord, yearRange));
        soft.assertAll();

    }
}


