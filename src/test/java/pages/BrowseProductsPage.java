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

    public BrowseProductsPage(WebDriver driver) {
        super(driver);
    }

    private String mensShoesSize(String size) {
        return format(SHOES_SIZE, size);
    }

    private String specificShoe(int shoeNumber) {
        return format(SPECIFIC_SHOE, shoeNumber);
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

    public void selectSpecificProduct(int productNumber) {
        scrollToElementInCenterOfBlock(driver.findElement(By.xpath(specificShoe(productNumber))), driver);
        waitUntilElementToBeClickable(specificShoe(productNumber)).click();
    }

    public void closeAdd() {
        waitUntilElementToBeClickable(CLOSE_ADD_BUTTON);
    }

    public List<String> getTitlesNames() {
        List<String> titles = new ArrayList<>();
        getAllProductNamesElements();
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

    public void waitLoading() {
        waitUntilVisibilityOfElementLocated(LOADING_ELEMENT);
        waitUntilInvisibilityOfElementLocated(LOADING_ELEMENT);
    }

    public String getGenderField(String gender) {
        return waitUntilElementToBeClickable(selectFilter(gender)).getText().replaceAll("[^a-zA-Z]", "").toLowerCase();
    }
}