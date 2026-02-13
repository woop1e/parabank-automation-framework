package negative;

import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.AccountOverviewPage;
import pages.BillPayPage;
import pages.HomePage;
import pages.RegisterPage;
import utils.LoggerUtil;

/**
 * NegativeTests - Negative and validation test scenarios
 */
public class NegativeTests extends BaseTest {

    @Test(priority = 1, description = "Verify login with invalid credentials")
    public void testLoginInvalidCredentials() {
        LoggerUtil.info("Starting test: Login with invalid credentials");
        
        HomePage homePage = new HomePage(driver);
        
        // Attempt login with invalid credentials
        AccountOverviewPage accountPage = homePage.login("invaliduser", "wrongpassword");
        
        // Verify error message is displayed
        boolean isErrorDisplayed = homePage.isErrorMessageDisplayed();
        Assert.assertTrue(isErrorDisplayed, "Error message not displayed for invalid credentials");
        
        // Verify error message content
        String errorMessage = homePage.getErrorMessage();
        Assert.assertTrue(errorMessage.length() > 0, "Error message is empty");
        
        LoggerUtil.info("Invalid credentials error displayed: " + errorMessage);
    }

    @Test(priority = 2, description = "Verify registration with empty required fields")
    public void testRegistrationEmptyRequiredFields() {
        LoggerUtil.info("Starting test: Registration with empty required fields");
        
        HomePage homePage = new HomePage(driver);
        
        // Navigate to Register page
        RegisterPage registerPage = homePage.clickRegister();
        
        // Click register button without filling any data
        registerPage.clickRegisterWithoutData();
        
        // Verify required field errors are displayed
        boolean areErrorsDisplayed = registerPage.areRequiredFieldErrorsDisplayed();
        Assert.assertTrue(areErrorsDisplayed, 
                         "Required field validation errors not displayed");
        
        LoggerUtil.info("Required field validation working correctly");
    }

    @Test(priority = 3, description = "Verify bill payment with invalid amount format")
    public void testBillPaymentInvalidAmountFormat() {
        LoggerUtil.info("Starting test: Bill payment with invalid amount format");
        
        HomePage homePage = new HomePage(driver);
        
        // Login
        AccountOverviewPage accountPage = homePage.login("john", "demo");
        Assert.assertTrue(accountPage.isAccountOverviewPageLoaded(), "Login failed");
        
        // Navigate to Bill Pay
        BillPayPage billPayPage = accountPage.clickBillPay();
        Assert.assertTrue(billPayPage.isBillPayPageLoaded(), "Bill Pay page did not load");
        
        // Try to pay bill with invalid amount format
        billPayPage.payBill(
            "Electric Company",
            "456 Power St",
            "Los Angeles",
            "CA",
            "90001",
            "555-9999",
            "987654",
            "abc123", // Invalid amount format
            "0"
        );
        
        // Verify error is displayed
        boolean isErrorDisplayed = billPayPage.isAmountErrorDisplayed();
        Assert.assertTrue(isErrorDisplayed, 
                         "Amount validation error not displayed for invalid format");
        
        LoggerUtil.info("Invalid amount format validation working correctly");
    }
}
