package utils;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.time.Duration;

/**
 * DriverFactory - Manages WebDriver initialization
 */
public class DriverFactory {
    private static final ThreadLocal<WebDriver> driver = new ThreadLocal<>();

    /**
     * Get WebDriver instance
     */
    public static WebDriver getDriver() {
        if (driver.get() == null) {
            driver.set(createDriver());
        }
        return driver.get();
    }

    /**
     * Create WebDriver based on browser type
     */
    private static WebDriver createDriver() {
        String browser = ConfigReader.getProperty("browser");
        WebDriver webDriver;

        switch (browser.toLowerCase()) {
            case "firefox":
                WebDriverManager.firefoxdriver().setup();
                webDriver = new FirefoxDriver();
                LoggerUtil.info("Firefox browser initialized");
                break;

            case "edge":
                WebDriverManager.edgedriver().setup();
                webDriver = new EdgeDriver();
                LoggerUtil.info("Edge browser initialized");
                break;

            case "chrome":
            default:
                WebDriverManager.chromedriver().setup();
                ChromeOptions options = new ChromeOptions();
                options.addArguments("--remote-allow-origins=*");
                options.addArguments("--disable-notifications");
                options.addArguments("--disable-blink-features=AutomationControlled");
                options.addArguments("--disable-dev-shm-usage");
                options.addArguments("--no-sandbox");
                options.addArguments("--start-maximized");
                options.addArguments("user-agent=Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/120.0.0.0 Safari/537.36");
                options.setExperimentalOption("excludeSwitches", new String[]{"enable-automation"});
                options.setExperimentalOption("useAutomationExtension", false);
                webDriver = new ChromeDriver(options);
                LoggerUtil.info("Chrome browser initialized");
                break;
        }

        // Set implicit wait
        int implicitWait = Integer.parseInt(ConfigReader.getProperty("implicit.wait"));
        webDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(implicitWait));

        return webDriver;
    }

    /**
     * Quit WebDriver
     */
    public static void quitDriver() {
        if (driver.get() != null) {
            try {
                driver.get().quit();
            } catch (Exception e) {
                LoggerUtil.error("Error while quitting driver: " + e.getMessage());
            } finally {
                driver.remove();
            }
        }
    }
    
    /**
     * Remove driver from ThreadLocal
     */
    public static void removeDriver() {
        driver.remove();
    }
}
