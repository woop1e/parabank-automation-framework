package base;

import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import utils.ConfigReader;
import utils.DriverFactory;
import utils.LoggerUtil;
import utils.ScreenshotUtils;

/**
 * BaseTest - Parent class for all Test Classes
 * Handles browser setup, teardown, and screenshot capture
 */
public class BaseTest {
    protected WebDriver driver;
    protected String baseUrl;

    @BeforeMethod(alwaysRun = true)
    public void setUp() {
        LoggerUtil.info("========== TEST EXECUTION STARTED ==========");
        
        try {
            // Ensure any previous driver is removed
            DriverFactory.removeDriver();
            
            // Initialize WebDriver
            driver = DriverFactory.getDriver();
            LoggerUtil.info("Browser launched successfully");

            // Load base URL from config
            baseUrl = ConfigReader.getProperty("base.url");
            driver.get(baseUrl);
            LoggerUtil.info("Navigated to application URL: " + baseUrl);
            
            // Maximize browser
            driver.manage().window().maximize();
            LoggerUtil.info("Browser window maximized");
        } catch (Exception e) {
            LoggerUtil.error("Error during test setup: " + e.getMessage());
            throw e;
        }
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown(ITestResult result) {
        // Capture screenshot on failure
        if (result.getStatus() == ITestResult.FAILURE) {
            LoggerUtil.error("TEST FAILED: " + result.getName());
            LoggerUtil.error("Failure Reason: " + result.getThrowable());
            
            try {
                if (driver != null) {
                    String screenshotPath = ScreenshotUtils.captureScreenshot(driver, result.getName());
                    LoggerUtil.info("Screenshot captured at: " + screenshotPath);
                }
            } catch (Exception e) {
                LoggerUtil.error("Failed to capture screenshot: " + e.getMessage());
            }
        } else if (result.getStatus() == ITestResult.SUCCESS) {
            LoggerUtil.info("TEST PASSED: " + result.getName());
        } else if (result.getStatus() == ITestResult.SKIP) {
            LoggerUtil.warn("TEST SKIPPED: " + result.getName());
        }

        // Close browser
        try {
            if (driver != null) {
                driver.quit();
                LoggerUtil.info("Browser closed successfully");
            }
        } catch (Exception e) {
            LoggerUtil.error("Error closing browser: " + e.getMessage());
        } finally {
            DriverFactory.removeDriver();
            driver = null;
        }
        
        LoggerUtil.info("========== TEST EXECUTION COMPLETED ==========\n");
    }
}
