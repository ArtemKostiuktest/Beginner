package pages;

import base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class SocksDescriptionPage extends BasePage {
    private final String NAME_OF_GOOD = "//h1[@class='product-info-js']";
    private final String PRICE_OF_GOOD = "//*[@id='product-info-container']//span[@data-amount]";
    private final String COLOR_OF_GOOD = "//section[@class='product-content-form-step-container step-container attr-color attr-color-js selection-js context-toggle-js clearfix']";

    public SocksDescriptionPage(WebDriver driver) {
        super(driver);
    }

    public boolean isNameOfGoodVisible() {
        return driver.findElement(By.xpath(NAME_OF_GOOD)).isDisplayed();
    }

    public boolean isPriceOfGoodVisible() {
        return driver.findElement(By.xpath(PRICE_OF_GOOD)).isDisplayed();
    }

    public boolean isColorOfGoodVisible() {
        return driver.findElement(By.xpath(COLOR_OF_GOOD)).isDisplayed();
    }
}
