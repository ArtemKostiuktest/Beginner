package pages;

import base.BasePage;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

public class HeaderFragment extends BasePage {
    private final String BASE_SEARCH_FIELD = "//input[@name ='searchTerm']";
    private final String MEN_BUTTON = "//a[@data-navigation='men']";
    private final String MEN_SHOES_VIEW_ALL = "//a[@data-navigation='MEN-SHOES:MEN-SHOES:men']";
    protected final String GIFT_CARDS = "//div[contains(@class,'cart-container')]//a[@data-navigation='gift']/span[@class='topnav-utility-item-label']";

    public HeaderFragment(WebDriver driver) {
        super(driver);
    }

    public void clickGiftCards() {
        waitUntilElementToBeClickable(GIFT_CARDS).click();
    }

    public void fillBaseSearchField(String searchField) {
        waitUntilElementToBeClickable(BASE_SEARCH_FIELD).sendKeys(searchField + Keys.ENTER);
    }

    public void openAllMenShoes() {
        Actions action = new Actions(driver);
        action.moveToElement(menCategoryElement()).perform();
        selectViewAllMenShoes();
    }

    private void selectViewAllMenShoes() {
        waitUntilElementToBeClickable(MEN_SHOES_VIEW_ALL).click();
    }

    private WebElement menCategoryElement() {
        return waitUntilVisibilityOfElementLocated(MEN_BUTTON);
    }
}