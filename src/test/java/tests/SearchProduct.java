package tests;

import base.AbstractBaseTest;
import org.assertj.core.api.SoftAssertions;
import org.testng.annotations.Test;
import pages.HomePage;
import pages.NamePage;

import java.util.List;

public class SearchProduct extends AbstractBaseTest {
    private final String nameOfSearchProduct = "Classic";
    List<String> listOfNames;

    @Test
    public void searchProductByName() {
        HomePage homePage = new HomePage(driver);
        SoftAssertions softAssert = new SoftAssertions();
        homePage.fillBaseSearchField(nameOfSearchProduct);

        NamePage namePage = new NamePage(driver);
        listOfNames = namePage.getProductsNames();
        for (String name : listOfNames) {
            softAssert.assertThat(name).contains(nameOfSearchProduct);
        }

        softAssert.assertAll();
    }
}