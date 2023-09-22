package pages;

import base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

import static java.lang.String.format;
import static org.openqa.selenium.By.xpath;
import static org.openqa.selenium.support.ui.ExpectedConditions.presenceOfAllElementsLocatedBy;
import static org.openqa.selenium.support.ui.ExpectedConditions.refreshed;
import static utils.Utils.getElementText;

public class BrowseProductsPage extends BasePage {

    private final String PRODUCT_TITLES = "//span[@class ='product-block-name-wrapper']";
    private final String SORT_OPTION = "//button[contains(@data-facet-value,'%s')]";
    private final String LOADING_ELEMENT = "//div[@id='loading-spinner']";
    private final String NAME_OF_TITLES = "//span[@class ='product-block-name-wrapper']";
    private final String SIZE = "//div[@class='filter-options']/ul/li/button[text()='%s']";
    private final String SPECIFIC_PRODUCT_TITLE = "//div[@data-product-line='inline'][%s]//span[@class='product-block-name-wrapper']";
    private final String SORT_BY_DROPDOWN = "//select[@id='sort-by']";
    private final String SPECIFIC_SORT_BY = "//select[@id='sort-by']/option[contains(text(),'%s')]";
    private final String PRODUST_PRICES = "//span[contains(@class,'offer')]";
    private final String SPECIFIC_PRICE = "(//span[contains(@class,'offer')])[%s]";
    private final String FILTER_OPTION = "//button/span[contains(text(),'%s')]";
    private final String FILTER_OPTION_COUNTER = "//button/span[contains(text(),'%s')]/span";
    private final String SHOES_PICK = "//figure[@class='product-block-figure']";
    private final String LOAD_MORE_BUTTON = "//a[contains(@class,'load-more')]";
    private final String SIGN_UP_EMAIL_FIELD = "//input[@id='newsletterInputField']";
    private final String NOT_FOUND_OPTION = "//div[@class='catalog-results catalog-results-js']";

    public BrowseProductsPage(WebDriver driver) {
        super(driver);
    }

    private String filterBy(String filter) {
        return format(FILTER_OPTION, filter);
    }

    private String filterCounter(String filter) {
        return format(FILTER_OPTION_COUNTER, filter);
    }

    private String sortBy(String sortValue) {
        return format(SPECIFIC_SORT_BY, sortValue);
    }

    private String mensShoesSize(String size) {
        return format(SIZE, size);
    }

    private String specificProduct(int shoeNumber) {
        return format(SPECIFIC_PRODUCT_TITLE, shoeNumber);
    }

    public void selectSortByDropdown() {
        waitUntilElementToBeClickable(SORT_BY_DROPDOWN).click();
    }

    public void selectSortBy(String sortValue) {
        waitUntilElementToBeClickable(sortBy(sortValue)).click();
    }

    public void selectFilterBy(String filterValue) {
        waitUntilElementToBeClickable(filterBy(filterValue)).click();
    }

    public int getFilterCounter(String filterValue) {
        return Integer.parseInt(waitUntilVisibilityOfElement(filterCounter(filterValue)).getText().replaceAll("[^0-9]", ""));
    }

    public void selectSizeInFilter(String size) {
        waitUntilVisibilityOfElementLocated(mensShoesSize(size)).click();
    }

    private int getProductElementsCount(String locator) {
        return waitPresenceOfElementsLocated(locator).size();
    }

    private String selectFilter(String filter) {
        return format(SORT_OPTION, filter);
    }

    public void selectFilterOption(String filter) {
        waitUntilVisibilityOfElement(selectFilter(filter)).click();
    }

    public List<WebElement> getAllProductNamesElements() {
        return waitPresenceOfElementsLocated(PRODUCT_TITLES);
    }

    public List<WebElement> getAllProductPricesElements() {
        return waitPresenceOfElementsLocated(PRODUST_PRICES);
    }

    public void selectSpecificProduct(int productNumber) {
        scrollToElementInCenterOfBlock(driver.findElement(By.xpath(specificProduct(productNumber))), driver);
        waitUntilElementToBeClickable(specificProduct(productNumber)).click();
    }

    public List<String> getTitlesNames() {
        List<String> titles = new ArrayList<>();
        for (WebElement element : getAllProductNamesElements()) {
            String title = getElementText(element);
            titles.add(title);
        }
        return titles;
    }

    public List<String> getTitlesNamesOneByOne(int currentIndex) {
        List<String> names = new ArrayList<>();

        while (currentIndex < getProductElementsCount(NAME_OF_TITLES)) {
            WebElement element = wait.until(refreshed(presenceOfAllElementsLocatedBy(xpath(NAME_OF_TITLES)))).get(currentIndex);
            scrollToElement(element, driver);

            String name = getElementText(element);
            names.add(name);
            currentIndex++;
        }
        return names;
    }

    public List<Double> getPrices() {
        List<Double> prices = new ArrayList<>();
        for (int i = 1; i <= getAllProductPricesElements().size(); i++) {
            prices.add(getSpecificPrice(i));
        }
        return prices;
    }

    private Double getSpecificPrice(int i) {
        return Double.parseDouble(waitUntilVisibilityOfElementLocated(format(SPECIFIC_PRICE, i)).getAttribute("data-amount"));
    }

    public void waitLoading() {
        waitUntilVisibilityOfElementLocated(LOADING_ELEMENT);
        waitUntilInvisibilityOfElementLocated(LOADING_ELEMENT);
    }

    public String getGenderField(String gender) {
        return waitUntilElementToBeClickable(selectFilter(gender)).getText().replaceAll("[^a-zA-Z]", "").toLowerCase();
    }

    public void selectPage() {
        waitUntilElementToBeClickable(SHOES_PICK).click();
    }

    public String selectFirstTitle(int shoeNumber) {
        return waitUntilElementToBeClickable(specificProduct(shoeNumber)).getText().toLowerCase();
    }

    public void loadMore() {
        waitUntilElementToBeClickable(LOAD_MORE_BUTTON).click();
    }

    public WebElement loadMoreElement() {
        return waitUntilElementToBeClickable(LOAD_MORE_BUTTON);
    }

    public void loadAll() {
        while (isElementDisplayed(driver, LOAD_MORE_BUTTON)) {
            scrollToElementInCenterOfBlock(loadMoreElement(), driver);
            loadMore();
            waitLoading();
        }
    }

    public boolean foundProducts() {
        return isElementDisplayed(driver, NOT_FOUND_OPTION);
    }
}