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
    private final String CLOSE_ADD_BUTTON = "//div[@data-label='Close']";
    private final String SHOES_SIZE = "//div[@class='filter-options']/ul/li/button[text()='%s']";
    private final String SPECIFIC_SHOE = "//div[@data-product-line='inline'][%s]//span[@class='product-block-name-wrapper']";
    private final String

    private String mensShoesSize(String size) {
        return format(SHOES_SIZE, size);
    }

    private String specificShoe(int shoeNumber) {
        return format(SPECIFIC_SHOE, shoeNumber);
    }

    public void selectSizeInFilter(String size) {
        waitUntilVisibilityOfElementLocated(mensShoesSize(size)).click();
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

    public void waitLoading() {
        waitUntilVisibilityOfElementLocated(LOADING_ELEMENT);
        waitUntilInvisibilityOfElementLocated(LOADING_ELEMENT);
    }
}