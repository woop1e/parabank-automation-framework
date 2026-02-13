package pages;

import base.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import utils.LoggerUtil;

/**
 * AccountOverviewPage - Page Object for Account Overview Page
 */
public class AccountOverviewPage extends BasePage {

    // Page Elements
    @FindBy(css = "h1.title")
    private WebElement pageTitle;

    @FindBy(linkText = "Log Out")
    private WebElement logoutLink;

    @FindBy(linkText = "Transfer Funds")
    private WebElement transferFundsLink;

    @FindBy(linkText = "Bill Pay")
    private WebElement billPayLink;

    @FindBy(linkText = "Accounts Overview")
    private WebElement accountsOverviewLink;

    @FindBy(css = "table#accountTable")
    private WebElement accountTable;

    @FindBy(css = "p.smallText")
    private WebElement welcomeMessage;

    // Constructor
    public AccountOverviewPage(WebDriver driver) {
        super(driver);
    }

    // Page Actions
    /**
     * Check if Account Overview page is loaded
     */
    public boolean isAccountOverviewPageLoaded() {
        try {
            waitForElementToBeVisible(pageTitle);
            boolean isLoaded = pageTitle.getText().contains("Accounts Overview");
            LoggerUtil.info("Account Overview page loaded: " + isLoaded);
            return isLoaded;
        } catch (Exception e) {
            LoggerUtil.error("Account Overview page not loaded: " + e.getMessage());
            return false;
        }
    }

    /**
     * Logout from application
     */
    public HomePage logout() {
        LoggerUtil.info("Logging out from application");
        clickElement(logoutLink);
        return new HomePage(driver);
    }

    /**
     * Navigate to Transfer Funds page
     */
    public TransferFundsPage clickTransferFunds() {
        LoggerUtil.info("Navigating to Transfer Funds page");
        clickElement(transferFundsLink);
        return new TransferFundsPage(driver);
    }

    /**
     * Navigate to Bill Pay page
     */
    public BillPayPage clickBillPay() {
        LoggerUtil.info("Navigating to Bill Pay page");
        clickElement(billPayLink);
        return new BillPayPage(driver);
    }

    /**
     * Get welcome message
     */
    public String getWelcomeMessage() {
        return getElementText(welcomeMessage);
    }

    /**
     * Check if account table is displayed
     */
    public boolean isAccountTableDisplayed() {
        return isElementDisplayed(accountTable);
    }
}
