package smoke;

import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.AccountOverviewPage;
import pages.HomePage;
import pages.TransferFundsPage;
import utils.LoggerUtil;

/**
 * SmokeTests - Critical smoke test scenarios
 */
public class SmokeTests extends BaseTest {

    @Test(priority = 1, description = "Verify homepage loads successfully")
    public void testHomepageLoadsSuccessfully() {
        LoggerUtil.info("Starting test: Homepage loads successfully");
        
        HomePage homePage = new HomePage(driver);
        
        // Verify homepage is loaded
        boolean isLoaded = homePage.isHomePageLoaded();
        Assert.assertTrue(isLoaded, "Homepage did not load successfully");
        
        // Verify page title
        String pageTitle = homePage.getPageTitle();
        Assert.assertTrue(pageTitle.contains("ParaBank"), "Page title does not contain 'ParaBank'");
        
        LoggerUtil.info("Homepage loaded successfully with title: " + pageTitle);
    }

    @Test(priority = 2, description = "Verify valid user login")
    public void testValidLogin() {
        LoggerUtil.info("Starting test: Valid user login");
        
        HomePage homePage = new HomePage(driver);
        
        // Login with valid credentials
        AccountOverviewPage accountPage = homePage.login("john", "demo");
        
        // Verify Account Overview page is loaded
        boolean isLoaded = accountPage.isAccountOverviewPageLoaded();
        Assert.assertTrue(isLoaded, "Account Overview page did not load after login");
        
        // Verify account table is displayed
        boolean isTableDisplayed = accountPage.isAccountTableDisplayed();
        Assert.assertTrue(isTableDisplayed, "Account table is not displayed");
        
        LoggerUtil.info("User logged in successfully");
    }

    @Test(priority = 3, description = "Verify logout functionality")
    public void testLogoutFunctionality() {
        LoggerUtil.info("Starting test: Logout functionality");
        
        HomePage homePage = new HomePage(driver);
        
        // Login
        AccountOverviewPage accountPage = homePage.login("john", "demo");
        Assert.assertTrue(accountPage.isAccountOverviewPageLoaded(), "Login failed");
        
        // Logout
        HomePage homePageAfterLogout = accountPage.logout();
        
        // Verify redirected to home page
        boolean isHomePageLoaded = homePageAfterLogout.isHomePageLoaded();
        Assert.assertTrue(isHomePageLoaded, "Not redirected to home page after logout");
        
        LoggerUtil.info("User logged out successfully");
    }

    @Test(priority = 4, description = "Verify Account Overview page loads")
    public void testAccountOverviewPageLoads() {
        LoggerUtil.info("Starting test: Account Overview page loads");
        
        HomePage homePage = new HomePage(driver);
        
        // Login
        AccountOverviewPage accountPage = homePage.login("john", "demo");
        
        // Verify page is loaded
        Assert.assertTrue(accountPage.isAccountOverviewPageLoaded(), 
                         "Account Overview page did not load");
        
        // Verify account table is present
        Assert.assertTrue(accountPage.isAccountTableDisplayed(), 
                         "Account table is not displayed");
        
        LoggerUtil.info("Account Overview page loaded successfully");
    }

    @Test(priority = 5, description = "Verify valid transfer funds transaction")
    public void testTransferFundsValidTransaction() {
        LoggerUtil.info("Starting test: Transfer funds valid transaction");
        
        HomePage homePage = new HomePage(driver);
        
        // Login
        AccountOverviewPage accountPage = homePage.login("john", "demo");
        Assert.assertTrue(accountPage.isAccountOverviewPageLoaded(), "Login failed");
        
        // Navigate to Transfer Funds
        TransferFundsPage transferPage = accountPage.clickTransferFunds();
        Assert.assertTrue(transferPage.isTransferFundsPageLoaded(), 
                         "Transfer Funds page did not load");
        
        // Transfer funds
        transferPage.transferFunds("100", "0", "1");
        
        // Verify transfer success
        boolean isSuccessful = transferPage.isTransferSuccessful();
        Assert.assertTrue(isSuccessful, "Transfer was not successful");
        
        LoggerUtil.info("Funds transferred successfully");
    }
}
