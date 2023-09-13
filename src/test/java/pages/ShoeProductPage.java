package pages;

import base.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class ShoeProductPage extends BasePage {

    public ShoeProductPage(WebDriver driver) {
        super(driver);
    }

    static final String SELECT_SIZE_BUTTON = "//div[@class='product-content-form-size-step-dropdown-container custom-dropdown-container']";

    public void clickSelectSizeButton() {
        waitUntilElementToBeClickable(SELECT_SIZE_BUTTON).click();
    }

    private String ValueOfSizeAccessibility(String size) {
        return "//div[@value='" + size + "']";
    }
    public String getValueOfSizeAccessibility(String size) {
        return waitUntilElementToBeClickable(ValueOfSizeAccessibility(size)).getText();
    }
}
