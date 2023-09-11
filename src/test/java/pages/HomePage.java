package pages;

import base.AbstractBaseTest;
import base.BasePage;
import org.openqa.selenium.WebDriver;

public class HomePage extends BasePage {
    public HomePage(WebDriver driver) {
        super(driver);
    }
}
//input[@name ='searchTerm']