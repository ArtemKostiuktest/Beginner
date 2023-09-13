package pages;

import base.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class MenShoesPage extends BasePage {

    public MenShoesPage(WebDriver driver) {
        super(driver);
    }
    private final String ALL_MENS_SHOES_NAMES = "//div[@data-product-line='inline']//span[@class='product-block-name-wrapper']";
    public List<WebElement> getAllProductNamesElements() {
        return waitPresenceOfElementsLocated(ALL_MENS_SHOES_NAMES);
    }

    private String mensShoesSize(String size) {
            return "//div[@class='filter-options']/ul/li/button[text()='" + size + "']";
    }
    public void selectMensShoesSize(String size) {
        waitUntilElementToBeClickable(mensShoesSize(size)).click();
    }
    private String specificShoe(int shoeNumber) {
        return "//div[@data-product-line='inline'][" + shoeNumber + "]//span[@class=\"product-block-name-wrapper\"]";
    }
    public void selectSpecificShoe(int shoeNumber) {
        waitUntilElementToBeClickable(specificShoe(shoeNumber)).click();
    }
}
