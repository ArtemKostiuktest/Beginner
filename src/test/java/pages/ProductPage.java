package pages;

import base.BasePage;
import org.openqa.selenium.WebDriver;

public class ProductPage extends BasePage {

    public ProductPage(WebDriver driver) {
        super(driver);
    }

    private final String SELECT_SIZE_BUTTON = "//div[@class='product-content-form-size-step-dropdown-container custom-dropdown-container']";

    public void SelectDropdownSizeButton() {
        waitUntilElementToBeClickable(SELECT_SIZE_BUTTON).click();
    }

    public String getValueOfSizeAccessibility(String size) {
        return waitUntilElementToBeClickable(valueOfSizeAccessibility(size)).getText();
    }

    private String valueOfSizeAccessibility(String size) {
        return "//div[@value='" + size + "']";
    }
}
