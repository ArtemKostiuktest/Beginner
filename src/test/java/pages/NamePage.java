package pages;

import base.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

import static utils.Utils.getElementText;

public class NamePage extends BasePage {
    protected final String PRODUCT_TITLES_SPAN = "//span[@class ='product-block-name-wrapper']";
    protected static final String SHOES_PICK = "(//figure[@class='product-block-figure'])[1]";

    public NamePage(WebDriver driver) {
        super(driver);
    }

    public List<String> getTitlesNames() {
        List<String> titles = new ArrayList<>();
        List<WebElement> productTitleElements = waitPresenceOfElementsLocated(PRODUCT_TITLES_SPAN);
        for (WebElement element : productTitleElements) {
            String title = getElementText(element);
            titles.add(title);
        }
        return titles;
    }
}