package tests;

import base.AbstractBaseTest;
import org.assertj.core.api.Assertions;
import org.assertj.core.api.SoftAssertions;
import org.testng.annotations.Test;
import pages.HomePage;
import pages.NamePage;

import java.util.List;
import java.util.concurrent.TimeoutException;

import static org.assertj.core.api.Assertions.assertThat;

public class SearchProduct extends AbstractBaseTest {
    private final String nameOfSearchProduct = "Shoes";
    List<String> titleNames;

    @Test(description = "Using filter in search")
    public void searchProductByName() {
        HomePage homePage = new HomePage(driver);
        NamePage namePage = new NamePage(driver);
        SoftAssertions soft = new SoftAssertions();

        homePage.fillBaseSearchField(nameOfSearchProduct);
        namePage
                .useToddlerFilter()
                .waitLoading();


        var toddlerFilterWord = "Toddler";
        var yearRange = "1-4 Years";

        titleNames = namePage.getTitlesNames();
        titleNames.forEach(title ->
                soft.assertThat(title)
                        .as("Запис не містить " + toddlerFilterWord + " та " + yearRange)
                        .contains(toddlerFilterWord, yearRange));
        soft.assertAll();
        
    }
}