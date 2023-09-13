package tests;

import base.AbstractBaseTest;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.HomePage;
import pages.MenShoesPage;
import pages.ShoeProductPage;

import java.util.ArrayList;
import java.util.List;

public class ShoesFilterTest extends AbstractBaseTest {

    private final String SHOES_SIZE = "2.5";
    List<String> listOfAccessibility = new ArrayList<>();
    List<WebElement> listOfProductsElement = new ArrayList<>();

    @Test
    public void sizeFilterTest() {
        HomePage homePage = new HomePage(driver);
        MenShoesPage menShoesPage = new MenShoesPage(driver);

        homePage.openAllMenShoes();

        menShoesPage.selectMensShoesSize(SHOES_SIZE);
        listOfProductsElement = menShoesPage.getAllProductNamesElements();
        checkingEachProductForSizeAvailability(listOfProductsElement);

        for (String result : listOfAccessibility) {
            Assert.assertFalse(result.contains("Out of Stock"));
        }
    }

    public void checkingEachProductForSizeAvailability(List<WebElement> elements) {
        for (int i = 1; i <= elements.size(); i++) {
            ShoeProductPage shoeProductPage = new ShoeProductPage(driver);
            MenShoesPage menShoesPage = new MenShoesPage(driver);

            menShoesPage.selectSpecificShoe(i);
            shoeProductPage.clickSelectSizeButton();
            listOfAccessibility.add(shoeProductPage.getValueOfSizeAccessibility(SHOES_SIZE));
            driver.navigate().back();
        }
    }
}
