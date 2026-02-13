package utils;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * ScreenshotUtils - Captures and saves screenshots
 */
public class ScreenshotUtils {
    private static final String SCREENSHOT_DIR = "screenshots/";

    /**
     * Capture screenshot and save with timestamp
     */
    public static String captureScreenshot(WebDriver driver, String testName) {
        // Create screenshots directory if not exists
        File screenshotDir = new File(SCREENSHOT_DIR);
        if (!screenshotDir.exists()) {
            screenshotDir.mkdirs();
        }

        // Generate timestamp
        String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String screenshotName = testName + "_" + timestamp + ".png";
        String screenshotPath = SCREENSHOT_DIR + screenshotName;

        try {
            // Capture screenshot
            TakesScreenshot ts = (TakesScreenshot) driver;
            File source = ts.getScreenshotAs(OutputType.FILE);
            File destination = new File(screenshotPath);

            // Copy file to destination
            FileUtils.copyFile(source, destination);
            LoggerUtil.info("Screenshot saved successfully: " + screenshotPath);
            
            return screenshotPath;
        } catch (IOException e) {
            LoggerUtil.error("Failed to capture screenshot: " + e.getMessage());
            return null;
        }
    }
}
