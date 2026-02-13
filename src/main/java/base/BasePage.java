package base;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.LoggerUtil;

import java.time.Duration;

/**
 * BasePage - Parent class for all Page Objects
 * Contains common methods used across all pages
 */
public class BasePage {
    protected WebDriver driver;
    protected WebDriverWait wait;
    private static final int TIMEOUT = 15;

    public BasePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(TIMEOUT));
        PageFactory.initElements(driver, this);
    }

    /**
     * Wait for element to be visible
     */
    protected void waitForElementToBeVisible(WebElement element) {
        wait.until(ExpectedConditions.visibilityOf(element));
    }

    /**
     * Wait for element to be clickable
     */
    protected void waitForElementToBeClickable(WebElement element) {
        wait.until(ExpectedConditions.elementToBeClickable(element));
    }

    /**
     * Click on element with wait
     */
    protected void clickElement(WebElement element) {
        waitForElementToBeClickable(element);
        element.click();
        LoggerUtil.info("Clicked on element: " + element.toString());
    }

    /**
     * Enter text into element
     */
    protected void enterText(WebElement element, String text) {
        waitForElementToBeVisible(element);
        element.clear();
        element.sendKeys(text);
        LoggerUtil.info("Entered text: " + text + " into element: " + element.toString());
    }

    /**
     * Get text from element
     */
    protected String getElementText(WebElement element) {
        waitForElementToBeVisible(element);
        String text = element.getText();
        LoggerUtil.info("Retrieved text: " + text);
        return text;
    }

    /**
     * Check if element is displayed
     */
    protected boolean isElementDisplayed(WebElement element) {
        try {
            waitForElementToBeVisible(element);
            return element.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * Get page title
     */
    public String getPageTitle() {
        String title = driver.getTitle();
        LoggerUtil.info("Current page title: " + title);
        return title;
    }

    /**
     * Get current URL
     */
    public String getCurrentUrl() {
        String url = driver.getCurrentUrl();
        LoggerUtil.info("Current URL: " + url);
        return url;
    }
}
