package base;

import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import static org.openqa.selenium.support.ui.ExpectedConditions.elementToBeClickable;
import static org.openqa.selenium.support.ui.ExpectedConditions.visibilityOfElementLocated;

@Slf4j
public class BasePage extends Assert {
    protected WebDriver driver;
    protected WebDriverWait wait;
    protected Actions actions;

    public BasePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofMillis(15000));
    }

    public WebElement waitUntilVisibilityOfElementLocated(String locator) {
        return wait.until(visibilityOfElementLocated(By.xpath(locator)));
    }

    public WebElement waitUntilElementToBeClickable(String locator) {
        return wait.until(elementToBeClickable(By.xpath(locator)));
    }

    protected List<WebElement> waitUntilVisibilityOfElementsLocated(String locator) {
        wait.until(visibilityOfElementLocated(By.xpath(locator)));
        return driver.findElements(By.xpath(locator));
    }

    protected List<WebElement> waitUntilElementsToBeClickable(String locator) {
        wait.until(elementToBeClickable(By.xpath(locator)));
        return driver.findElements(By.xpath(locator));
    }

    public void waitUntilUrlContainsText(String urlPath) {
        wait.until(ExpectedConditions.urlContains(urlPath));
    }

    public void waitUntilNumberOfTabToBe(int tabNumber) {
        wait.until(ExpectedConditions.numberOfWindowsToBe(tabNumber));
    }

    protected List<WebElement> waitPresenceOfElementsLocated(String locator) {
        try {
            return wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath(locator)));
        } catch (WebDriverException e) {
            fail("No presence elements: " + locator);
            return null;
        }
    }

    public void goToTab(int tabNumber) {
        waitUntilNumberOfTabToBe(tabNumber);
        ArrayList<String> tabs = new ArrayList<>(driver.getWindowHandles());
        driver.switchTo().window(tabs.get(tabNumber - 1));
    }

    protected void waitUntilInvisibilityOfElementLocated(String locator) {
        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(locator)));
    }

    public String getUrl() {
        return driver.getCurrentUrl();
    }

    protected boolean isUrlContains(String urlPath) {
        return driver.getCurrentUrl().contains(urlPath);
    }

    public void moveCursor(WebElement element, WebDriver driver) {
        actions.moveToElement(element).build().perform();
    }

    public void doubleClick(WebElement element, WebDriver driver) {actions.doubleClick(element).build().perform();}

    public void scrollToElement(WebElement element, WebDriver driver) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
    }

    public void scrollToBottomOfThePage(WebDriver driver) {
        ((JavascriptExecutor) driver).executeScript("window.scrollTo(0, document.body.scrollHeight)");
    }

    public void scroll(int pixels) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollBy(0," + pixels + ")", "");
    }
}