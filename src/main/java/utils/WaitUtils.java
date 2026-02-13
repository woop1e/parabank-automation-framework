package utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

/**
 * WaitUtils - Custom wait utilities
 */
public class WaitUtils {
    private static final int DEFAULT_TIMEOUT = 15;

    /**
     * Wait for element to be visible
     */
    public static void waitForVisibility(WebDriver driver, WebElement element, int timeout) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
        wait.until(ExpectedConditions.visibilityOf(element));
    }

    /**
     * Wait for element to be visible with default timeout
     */
    public static void waitForVisibility(WebDriver driver, WebElement element) {
        waitForVisibility(driver, element, DEFAULT_TIMEOUT);
    }

    /**
     * Wait for element to be clickable
     */
    public static void waitForClickability(WebDriver driver, WebElement element, int timeout) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
        wait.until(ExpectedConditions.elementToBeClickable(element));
    }

    /**
     * Wait for element to be clickable with default timeout
     */
    public static void waitForClickability(WebDriver driver, WebElement element) {
        waitForClickability(driver, element, DEFAULT_TIMEOUT);
    }

    /**
     * Wait for page title to contain text
     */
    public static void waitForTitleContains(WebDriver driver, String title, int timeout) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
        wait.until(ExpectedConditions.titleContains(title));
    }

    /**
     * Custom sleep (use sparingly)
     */
    public static void sleep(int seconds) {
        try {
            Thread.sleep(seconds * 1000L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
