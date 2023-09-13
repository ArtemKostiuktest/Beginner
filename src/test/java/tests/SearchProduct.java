package tests;

import base.AbstractBaseTest;
import jdk.jfr.Description;
import org.assertj.core.api.SoftAssertions;
import org.testng.annotations.Test;
import pages.HomePage;
import pages.NamePage;

import java.util.List;

public class SearchProduct extends AbstractBaseTest {
    private final String nameOfSearchProduct = "Classic";
    List<String> listOfNames;

    @Test(description = "Product search by name")
    public void searchProductByName() {
        HomePage homePage = new HomePage(driver);
        NamePage namePage = new NamePage(driver);
        SoftAssertions softAssert = new SoftAssertions();

        homePage.fillBaseSearchField(nameOfSearchProduct);

        listOfNames = namePage.getTitlesNames();
        for (String name : listOfNames) {
            softAssert.assertThat(name).contains(nameOfSearchProduct);
        }
        softAssert.assertAll();
    }
}
