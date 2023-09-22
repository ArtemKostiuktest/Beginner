package tests;

import base.AbstractBaseTest;
import org.assertj.core.api.SoftAssertions;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;
import pages.*;

import java.util.ArrayList;
import java.util.List;

import static utils.UserData.*;

public class FiltersTest extends AbstractBaseTest {

    private HeaderFragment headerFragment;
    private BrowseProductsPage browseProductsPage;
    private ProductPage productPage;
    private SoftAssertions softAssert;
    private CartPage cartPage;
    private BillingAddressPage billingAddressPage;
    private PaymentPage paymentPage;

    private final int NUMBER_OF_PRODUCT = 1;
    private final String SHOES_SIZE = "2.5";
    private final String SIZE_ACCESSIBILITY_TARGET = "Out of Stock";
    private final String SORT_VALUE = "Low to High";
    private final String FILTER_VALUE = "Old Skool";
    private String allBillingInfo;
    private int filterProductValue;

    private List<String> listOfAccessibility = new ArrayList<>();
    private List<Double> listOfProductsPrices = new ArrayList<>();
    private List<String> listOfProductsTitles = new ArrayList<>();
    private List<WebElement> productsOptions = new ArrayList<>();

    public void preconditions() {
        headerFragment = new HeaderFragment(driver);
        browseProductsPage = new BrowseProductsPage(driver);
        productPage = new ProductPage(driver);
        cartPage = new CartPage(driver);
        billingAddressPage = new BillingAddressPage(driver);
        paymentPage = new PaymentPage(driver);
        softAssert = new SoftAssertions();

        headerFragment.openAllMenShoes();
    }

    @Test(description = "Filtering products by size")
    public void sizeFilterTest() {
        preconditions();
        browseProductsPage.selectSizeInFilter(SHOES_SIZE);
        productsOptions = browseProductsPage.getAllProductNamesElements();
        checkingEachProductForSizeAvailability(productsOptions);

        for (String result : listOfAccessibility) {
            softAssert.assertThat(result).doesNotContain(SIZE_ACCESSIBILITY_TARGET);
        }
        softAssert.assertAll();
    }

    @Test(description = "Filtering products by price(lower to high)")
    public void priceFilterTest() {
        preconditions();
        browseProductsPage.selectSortByDropdown();
        browseProductsPage.selectSortBy(SORT_VALUE);
        productsOptions = browseProductsPage.getAllProductPricesElements();
        listOfProductsPrices = browseProductsPage.getPrices();

        for (int i = 1; i < listOfProductsPrices.size(); i++) {
            double currentValue = listOfProductsPrices.get(i);
            double previousValue = listOfProductsPrices.get(i - 1);
            softAssert.assertThat(currentValue >= previousValue);
        }
        softAssert.assertAll();
    }

    @Test(description = "Filtering products by Silhouette")
    public void silhouetteFilterTest() {
        preconditions();
        browseProductsPage.selectFilterBy(FILTER_VALUE);

        for (String name : browseProductsPage.getTitlesNames()) {
            softAssert.assertThat(name).contains(FILTER_VALUE.toLowerCase());
        }
        softAssert.assertAll();
    }

    @Test(description = "Checking the product value of the filter")
    public void filterProductValueTest() {
        preconditions();
        filterProductValue = browseProductsPage.getFilterCounter(FILTER_VALUE);
        browseProductsPage.selectFilterBy(FILTER_VALUE);
        browseProductsPage.loadAll();

        softAssert.assertThat(filterProductValue).isEqualTo(browseProductsPage.getTitlesNames().size());
    }

    @Test(description = "Using filters while shopping")
    public void filterWhileShoppingTest() {
        preconditions();
        browseProductsPage.selectSizeInFilter(SHOES_SIZE);
        filterProductValue = browseProductsPage.getFilterCounter(FILTER_VALUE);
        browseProductsPage.selectFilterBy(FILTER_VALUE);
        browseProductsPage.selectSortByDropdown();
        browseProductsPage.selectSortBy(SORT_VALUE);
        browseProductsPage.loadAll();
        listOfProductsPrices = browseProductsPage.getPrices();
        listOfProductsTitles = browseProductsPage.getTitlesNames();

        softAssert.assertThat(filterProductValue).isEqualTo(listOfProductsTitles.size());
        for (String name : listOfProductsTitles) {
            softAssert.assertThat(name).contains(FILTER_VALUE.toLowerCase());
        }
        for (int i = 1; i < listOfProductsPrices.size(); i++) {
            double currentValue = listOfProductsPrices.get(i);
            double previousValue = listOfProductsPrices.get(i - 1);
            softAssert.assertThat(currentValue >= previousValue);
        }

        browseProductsPage.selectSpecificProduct(NUMBER_OF_PRODUCT);
        productPage.selectSizeDropdown();

        softAssert.assertThat(productPage.getValueOfSizeAccessibility(SHOES_SIZE)).doesNotContain(SIZE_ACCESSIBILITY_TARGET);

        productPage.selectSize(SHOES_SIZE);
        productPage.addToCart();
        productPage.proceedToCheckout();
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
            softAssert.assertThat(allBillingInfo).contains(data);
        }

        paymentPage.enterCartForm(CARD_NUM, CARD_MONTH_BUT, CARD_YEAR_BUT, SECURITY_CODE, NAME_OF_HOLDER);

        softAssert.assertThat(paymentPage.isMakePaymentEnabled());

        softAssert.assertAll();
    }

    private void checkingEachProductForSizeAvailability(List<WebElement> elements) {
        for (int i = 1; i <= elements.size(); i++) {
            browseProductsPage.selectSpecificProduct(i);
            productPage.SelectDropdownSizeButton();
            listOfAccessibility.add(productPage.getValueOfSizeAccessibility(SHOES_SIZE));
            navigateGoBack();
            try {
                browseProductsPage.waitLoading();
            } catch (TimeoutException e) {
                System.out.println("Find Exception: Timeout exception");
            }
        }
    }
}
