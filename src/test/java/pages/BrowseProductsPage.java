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
    private final String FILTER_TODDLER = "//span[contains(text(),'Toddler')][@class=\"facet-text\"]";
    private final String LOADING_ELEMENT = "//div[@id='loading-spinner']";
    private final String CLOSE_ADD_BUTTON = "//div[@data-label='Close']";
    private final String SHOES_SIZE = "//div[@class='filter-options']/ul/li/button[text()='%s']";
    private final String SPECIFIC_SHOE = "//div[@data-product-line='inline'][%s]//span[@class='product-block-name-wrapper']";

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

    public BrowseProductsPage useToddlerFilter() {
        waitUntilElementToBeClickable(FILTER_TODDLER).click();

        return this;
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

    public List<String> getTitlesNamesOneByOne() {
        List<String> names = new ArrayList<>();
        int currentIndex = 0;
        String locator = "//span[@class ='product-block-name-wrapper']";

        while (currentIndex < getProductElementsCount(locator)) {
            WebElement element = wait.until(refreshed(presenceOfAllElementsLocatedBy(xpath(locator)))).get(currentIndex);
            scrollToElement( element, driver);

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
}