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
    private final String FILTER_OPTION = "//button[contains(@data-facet-value,'%s')]";
    private final String LOADING_ELEMENT = "//div[@id='loading-spinner']";
    private final String CLOSE_ADD_BUTTON = "//div[@data-label='Close']";
    private final String SHOES_SIZE = "//div[@class='filter-options']/ul/li/button[text()='%s']";
    private final String SPECIFIC_SHOE = "//div[@data-product-line='inline'][%s]//span[@class='product-block-name-wrapper']";
    private final String NAME_OF_TITLES = "//span[@class ='product-block-name-wrapper']";
    private final String SIZE = "//div[@class='filter-options']/ul/li/button[text()='%s']";
    private final String SPECIFIC_PRODUCT = "//div[@data-product-line='inline'][%s]//span[@class='product-block-name-wrapper']";
    private final String SORT_BY_DROPDOWN = "//select[@id='sort-by']";
    private final String PRICES_LOW_TO_HIGH = "//select[@id='sort-by']/option[@value='6']";
    private final String PRODUST_PRICES = "//span[contains(@class,'offer')]";
    private final String SPECIFIC_PRICE = "(//span[contains(@class,'offer')])[%s]";
    protected final String SHOES_PICK = "//figure[@class='product-block-figure']";

    public BrowseProductsPage(WebDriver driver) {
        super(driver);
    }

    private String mensShoesSize(String size) {
        return format(SIZE, size);
    }

    private String specificShoe(int shoeNumber) {
        return format(SPECIFIC_PRODUCT, shoeNumber);
    }

    public void selectSortByDropdown() {
        waitUntilElementToBeClickable(SORT_BY_DROPDOWN).click();
    }

    public void sortPricesLowToHigh() {
        waitUntilElementToBeClickable(PRICES_LOW_TO_HIGH).click();
    }

    public void selectSizeInFilter(String size) {
        waitUntilVisibilityOfElementLocated(mensShoesSize(size)).click();
    }

    private int getProductElementsCount(String locator) {
        return waitPresenceOfElementsLocated(locator).size();
    }

    private String selectFilter(String filter) {
        return format(FILTER_OPTION, filter);
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
        scrollToElementInCenterOfBlock(driver.findElement(By.xpath(specificShoe(productNumber))), driver);
        waitUntilElementToBeClickable(specificShoe(productNumber)).click();
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
        return Double.parseDouble(waitUntilVisibilityOfElementLocated(format(SPECIFIC_PRICE,i)).getAttribute("data-amount"));
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
}