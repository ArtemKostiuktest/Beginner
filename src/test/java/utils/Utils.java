package utils;

import org.openqa.selenium.WebElement;

public class Utils {
    public static String getElementText(WebElement element) {
        return element.getText().toLowerCase();
    }
}
