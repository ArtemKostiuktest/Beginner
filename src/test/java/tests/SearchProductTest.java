package tests;

import base.AbstractBaseTest;
import org.assertj.core.api.SoftAssertions;
import org.testng.annotations.Test;
import pages.BrowseProductsPage;
import pages.HeaderFragment;
import pages.ProductPage;

import java.util.List;

public class SearchProductTest extends AbstractBaseTest {

    private final String nameOfSearchProduct = "classic";
    private final String toddler = "toddler";
    private final String yearRange = "1-4 years";
    private final String nameOfSearchProducts = "Shoes";
    private final int numberOfProduct = 1;
    private final String shoesSize = "2.5";
    private String genderFilterText;

    List<String> titleNames;
    List<String> listOfNames;

    @Test(description = "Product search by name")
    public void searchProductByName() {
        HeaderFragment headerFragment = new HeaderFragment(driver);
        BrowseProductsPage browseProductsPage = new BrowseProductsPage(driver);
        SoftAssertions softAssert = new SoftAssertions();

        headerFragment.fillBaseSearchField(nameOfSearchProduct);

        listOfNames = browseProductsPage.getTitlesNames();
        for (String name : listOfNames) {
            softAssert.assertThat(name).contains(nameOfSearchProduct);
        }
        softAssert.assertAll();
    }

    @Test(description = "Using filter in search")
    public void searchesProductByName() {
        HeaderFragment headerFragment = new HeaderFragment(driver);
        BrowseProductsPage browseProductsPage = new BrowseProductsPage(driver);
        SoftAssertions soft = new SoftAssertions();

        headerFragment.fillBaseSearchField(nameOfSearchProducts);
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

    @Test(description = "Using a keyword in search")
    public void usingKeywordInSearch(){
        HeaderFragment headerFragment = new HeaderFragment(driver);
        BrowseProductsPage browseProductsPage = new BrowseProductsPage(driver);
        ProductPage productPage = new ProductPage(driver);

        headerFragment.fillBaseSearchField(nameOfSearchProducts);
        browseProductsPage.selectSpecificProduct(numberOfProduct);
        productPage.SelectDropdownSizeButton();
        productPage.selectSize(shoesSize);


    }
}