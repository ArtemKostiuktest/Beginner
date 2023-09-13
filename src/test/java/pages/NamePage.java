package pages;

import base.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

import static java.lang.Integer.parseInt;
import static utils.Utils.reName;

public class NamePage extends BasePage {
    public NamePage(WebDriver driver) {
        super(driver);
    }

    public List<String> getProductsNames() {
        List<String> names = new ArrayList<>();
        List<WebElement> productNameElements = waitPresenceOfElementsLocated("//span[@class ='product-block-name-wrapper']");
        for (WebElement element : productNameElements) {
            String name = reName(element);
            names.add(name);
        }
        return names;
    }
}