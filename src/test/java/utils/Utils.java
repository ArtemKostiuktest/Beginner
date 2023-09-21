package utils;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class Utils {
    public static String getElementText(WebElement element) {
        return element.getText().toLowerCase();
    }

    public static WebDriver switchToIframe(WebDriver driver, String locator) {
        WebElement element = driver.findElement(By.xpath(locator));
        return driver.switchTo().frame(element);
    }
}
