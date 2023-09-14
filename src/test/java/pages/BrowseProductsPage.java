package pages;

import base.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

import static java.lang.Integer.parseInt;
import static utils.Utils.getElementText;

public class BrowseProductsPage extends BasePage {
    public BrowseProductsPage(WebDriver driver) {
        super(driver);
    }

    private final String PRODUCT_TITLES = "//span[@class ='product-block-name-wrapper']";
    private final String LOADING_ELEMENT = "//div[@id='loading-spinner']";

    private String mensShoesSize(String size) {
        return "//div[@class='filter-options']/ul/li/button[text()='" + size + "']";
    }

    private String specificShoe(int shoeNumber) {
        return "//div[@data-product-line='inline'][" + shoeNumber + "]//span[@class=\"product-block-name-wrapper\"]";
    }

    public void selectSizeInFilter(String size) {
        waitUntilVisibilityOfElementLocated(mensShoesSize(size)).click();
    }

    public List<WebElement> getAllProductNamesElements() {
        return waitPresenceOfElementsLocated(PRODUCT_TITLES);
    }

    public void selectSpecificProduct(int productNumber) {
        waitUntilElementToBeClickable(specificShoe(productNumber)).click();
    }

    public List<String> getTitlesNames() {
        List<String> titles = new ArrayList<>();
        List<WebElement> productTitleElements = waitPresenceOfElementsLocated(PRODUCT_TITLES);
        for (WebElement element : productTitleElements) {
            String title = getElementText(element);
            titles.add(title);
        }
        return titles;
    }

    public void skipLoading() {
        waitUntilVisibilityOfElementLocated(LOADING_ELEMENT);
        waitUntilInvisibilityOfElementLocated(LOADING_ELEMENT);
    }
}