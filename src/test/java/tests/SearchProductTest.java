package tests;

import base.AbstractBaseTest;
import org.assertj.core.api.SoftAssertions;
import org.testng.annotations.Test;
import pages.BrowseProductsPage;
import pages.HomePage;

import java.util.List;

public class SearchProductTest extends AbstractBaseTest {

    private final String nameOfSearchProduct = "classic";
    private final String toddler = "toddler";
    private final String yearRange = "1-4 years";
    private final String nameOfSearchProducts = "Shoes";
    private String genderFilterText;

    List<String> titleNames;
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

    @Test(description = "Using filter in search")
    public void searchesProductByName() {
        HomePage homePage = new HomePage(driver);
        BrowseProductsPage browseProductsPage = new BrowseProductsPage(driver);
        SoftAssertions soft = new SoftAssertions();

        homePage.fillBaseSearchField(nameOfSearchProducts);
        browseProductsPage.selectFilterOption(toddler);
        browseProductsPage.waitLoading();
        genderFilterText = browseProductsPage.getGenderField(toddler);

        titleNames = browseProductsPage.getTitlesNamesOneByOne(0);
        titleNames.forEach(title ->
                soft.assertThat(title)
                        .as("Запис не містить " + genderFilterText + " та " + yearRange)
                        .contains(genderFilterText, yearRange));
        soft.assertAll();
    }
}