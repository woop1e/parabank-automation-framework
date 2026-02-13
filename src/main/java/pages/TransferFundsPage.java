package pages;

import base.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;
import utils.LoggerUtil;

/**
 * TransferFundsPage - Page Object for Transfer Funds Page
 */
public class TransferFundsPage extends BasePage {

    // Page Elements
    @FindBy(id = "amount")
    private WebElement amountField;

    @FindBy(id = "fromAccountId")
    private WebElement fromAccountDropdown;

    @FindBy(id = "toAccountId")
    private WebElement toAccountDropdown;

    @FindBy(css = "input[type='submit'][value='Transfer']")
    private WebElement transferButton;

    @FindBy(css = "div#showResult h1")
    private WebElement resultTitle;

    @FindBy(css = "div#showResult p")
    private WebElement resultMessage;

    @FindBy(css = "p.error")
    private WebElement errorMessage;

    @FindBy(css = "h1.title")
    private WebElement pageTitle;

    // Constructor
    public TransferFundsPage(WebDriver driver) {
        super(driver);
    }

    // Page Actions
    /**
     * Check if Transfer Funds page is loaded
     */
    public boolean isTransferFundsPageLoaded() {
        try {
            waitForElementToBeVisible(pageTitle);
            boolean isLoaded = pageTitle.getText().contains("Transfer Funds");
            LoggerUtil.info("Transfer Funds page loaded: " + isLoaded);
            return isLoaded;
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * Transfer funds between accounts
     */
    public void transferFunds(String amount, String fromAccountIndex, String toAccountIndex) {
        LoggerUtil.info("Transferring funds - Amount: " + amount);
        
        enterText(amountField, amount);
        
        Select fromAccount = new Select(fromAccountDropdown);
        fromAccount.selectByIndex(Integer.parseInt(fromAccountIndex));
        
        Select toAccount = new Select(toAccountDropdown);
        toAccount.selectByIndex(Integer.parseInt(toAccountIndex));
        
        clickElement(transferButton);
    }

    /**
     * Check if transfer was successful
     */
    public boolean isTransferSuccessful() {
        try {
            waitForElementToBeVisible(resultTitle);
            String title = resultTitle.getText();
            boolean isSuccessful = title.contains("Transfer Complete");
            LoggerUtil.info("Transfer successful: " + isSuccessful);
            return isSuccessful;
        } catch (Exception e) {
            LoggerUtil.error("Transfer not successful: " + e.getMessage());
            return false;
        }
    }

    /**
     * Get error message
     */
    public String getErrorMessage() {
        try {
            waitForElementToBeVisible(errorMessage);
            return getElementText(errorMessage);
        } catch (Exception e) {
            return "";
        }
    }

    /**
     * Check if error message is displayed
     */
    public boolean isErrorMessageDisplayed() {
        return isElementDisplayed(errorMessage);
    }
}
