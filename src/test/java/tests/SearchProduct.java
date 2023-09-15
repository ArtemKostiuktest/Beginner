package tests;

import base.AbstractBaseTest;
import org.assertj.core.api.SoftAssertions;
import org.testng.annotations.Test;
import pages.HomePage;
import pages.BrowseProductsPage;

import java.util.List;

public class SearchProduct extends AbstractBaseTest {

    private final String nameOfSearchProduct = "Classic";

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
}