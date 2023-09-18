package pages;

import base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

import static java.lang.String.format;
import static utils.Utils.getElementText;

public class BrowseProductsPage extends BasePage {
    public BrowseProductsPage(WebDriver driver) {
        super(driver);
    }

    private final String PRODUCT_TITLES = "//span[@class ='product-block-name-wrapper']";
    private final String LOADING_ELEMENT = "//div[@id='loading-spinner']";
    private final String SIZE = "//div[@class='filter-options']/ul/li/button[text()='%s']";
    private final String SPECIFIC_PRODUCT = "//div[@data-product-line='inline'][%s]//span[@class='product-block-name-wrapper']";
    private final String SORT_BY_DROPDOWN = "//select[@id='sort-by']";
    private final String PRICES_LOW_TO_HIGH = "//select[@id='sort-by']/option[@value='6']";
    private final String PRODUST_PRICES = "//span[contains(@class,'offer')]";
    private final String SPECIFIC_PRICE = "(//span[contains(@class,'offer')])[%s]";

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
}