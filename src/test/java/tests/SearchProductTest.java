package tests;

import base.AbstractBaseTest;
import org.assertj.core.api.SoftAssertions;
import org.testng.annotations.Test;
import pages.*;

import java.util.List;

import static utils.UserData.*;

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
//    String[] dataToCheckInGiftCard = UserData.dataToCheckInGiftCard;

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
    public void usingKeywordInSearch() {
        HeaderFragment headerFragment = new HeaderFragment(driver);
        BrowseProductsPage browseProductsPage = new BrowseProductsPage(driver);
        ProductPage productPage = new ProductPage(driver);
        CartPage cartPage = new CartPage(driver);
        BillingAddressPage billingAddressPage = new BillingAddressPage(driver);
        SoftAssertions soft = new SoftAssertions();

        headerFragment.fillBaseSearchField(nameOfSearchProducts);
        soft.assertThat(browseProductsPage.selectFirstTitle(numberOfProduct)).contains(nameOfSearchProducts.toLowerCase());
        browseProductsPage.selectSpecificProduct(numberOfProduct);
        soft.assertThat(productPage.selectTitleProduct()).contains(nameOfSearchProducts.toLowerCase());
        productPage.SelectDropdownSizeButton();
        productPage.selectSize(shoesSize);
        productPage.addToCart();
        productPage.proceedToCheckout();
        soft.assertThat(cartPage.getProductTitle()).contains(nameOfSearchProducts.toLowerCase());
        cartPage.checkoutSecurely();

        billingAddressPage.setFirstName(USER_FIRST_NAME);
        billingAddressPage.setLastName(USER_LAST_NAME);
        billingAddressPage.setAddress(ADDRESS);
        billingAddressPage.setCityName(UN_CITY);
        billingAddressPage.setPostCode(UN_POST_CODE);
        billingAddressPage.setPhoneNumber(USER_PHONE_NUMBER);
        billingAddressPage.setEmail(USER_EMAIL);
        billingAddressPage.acceptTerms();
        billingAddressPage.proceedPayment();


        List<String> dataList = billingAddressPage.getInfoAboutPaymentData();


        soft.assertThat(dataList.size())
                .as("Sizes of list and array do not match.")
                .isEqualTo(dataPay.length);

        for (int i = 0; i < dataPay.length - 1; i++) {
            soft.assertThat(dataList.get(i))
                    .as("Mismatch at index " + i)
                    .isEqualTo(dataPay[i].toLowerCase());
        }


        soft.assertAll();
    }
}