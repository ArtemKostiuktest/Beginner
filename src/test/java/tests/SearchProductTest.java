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
    private final String invalidQuery = "*";
    private final String caseInsensitiveQuery = "SK8-Hi";
    private String genderFilterText;
    private String allBillingInfo;

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
    public void usingKeywordInSearch() {
        HeaderFragment headerFragment = new HeaderFragment(driver);
        BrowseProductsPage browseProductsPage = new BrowseProductsPage(driver);
        ProductPage productPage = new ProductPage(driver);
        CartPage cartPage = new CartPage(driver);
        PaymentPage paymentPage = new PaymentPage(driver);
        BillingAddressPage billingAddressPage = new BillingAddressPage(driver);
        SoftAssertions soft = new SoftAssertions();

        headerFragment.fillBaseSearchField(nameOfSearchProducts);
        soft.assertThat(browseProductsPage.getFirstTitle()).contains(nameOfSearchProducts.toLowerCase());
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
        allBillingInfo = paymentPage.getAllBillingInfo();

        for (String data : dataPay) {
            soft.assertThat(allBillingInfo).contains(data);
            System.out.println(data);
        }

        paymentPage.enterCartForm(CARD_NUM, CARD_MONTH_BUT, CARD_YEAR_BUT, SECURITY_CODE, NAME_OF_HOLDER);
        soft.assertThat(paymentPage.isMakePaymentEnabled());

        soft.assertAll();
    }

    @Test(description = "Search with invalid query")
    public void searchWithInvalidQuery() {
        HeaderFragment headerFragment = new HeaderFragment(driver);
        SoftAssertions soft = new SoftAssertions();
        BrowseProductsPage browseProductsPage = new BrowseProductsPage(driver);

        headerFragment.fillBaseSearchField(invalidQuery);

        soft.assertThat(browseProductsPage.foundProducts());
    }

    @Test
    public void searchUsingCaseInsensitiveQuery() {
        HeaderFragment headerFragment = new HeaderFragment(driver);
        SoftAssertions soft = new SoftAssertions();
        BrowseProductsPage browseProductsPage = new BrowseProductsPage(driver);

        headerFragment.fillBaseSearchField(caseInsensitiveQuery);

        soft.assertThat(browseProductsPage.getFirstTitle()).contains(caseInsensitiveQuery.toLowerCase());
    }
}