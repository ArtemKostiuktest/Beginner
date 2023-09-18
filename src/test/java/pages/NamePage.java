package pages;

import base.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

import static java.lang.Integer.parseInt;
import static org.openqa.selenium.By.xpath;
import static org.openqa.selenium.support.ui.ExpectedConditions.presenceOfAllElementsLocatedBy;
import static org.openqa.selenium.support.ui.ExpectedConditions.refreshed;
import static utils.Utils.getElementText;

public class NamePage extends BasePage {
    protected final String PRODUCT_TITLES_SPAN = "//span[@class ='product-block-name-wrapper']";
    private final String FILTER_TODDLER = "//span[contains(text(),'Toddler')][@class=\"facet-text\"]";
    private final String LOADING_ELEMENT = "//div[@id='loading-spinner']";
    private int getProductElementsCount(String locator) {
        return waitPresenceOfElementsLocated(locator).size();
    }

    public NamePage(WebDriver driver) {
        super(driver);
    }

    public NamePage useToddlerFilter() {
        waitUntilElementToBeClickable(FILTER_TODDLER).click();

        return this;
    }

public List<String> getTitlesNames() {
    List<String> names = new ArrayList<>();
    int currentIndex = 0;
    String locator = "//span[@class ='product-block-name-wrapper']"; // Ваш XPath

    while (currentIndex < getProductElementsCount(locator)) {
        WebElement element = wait.until(refreshed(presenceOfAllElementsLocatedBy(xpath(locator)))).get(currentIndex);
        scrollToElement( element, driver);

        String name = getElementText(element); // Витягуємо назву продукту
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