package pages;

import base.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;
import utils.LoggerUtil;

/**
 * BillPayPage - Page Object for Bill Payment Page
 */
public class BillPayPage extends BasePage {

    // Page Elements
    @FindBy(name = "payee.name")
    private WebElement payeeNameField;

    @FindBy(name = "payee.address.street")
    private WebElement payeeAddressField;

    @FindBy(name = "payee.address.city")
    private WebElement payeeCityField;

    @FindBy(name = "payee.address.state")
    private WebElement payeeStateField;

    @FindBy(name = "payee.address.zipCode")
    private WebElement payeeZipCodeField;

    @FindBy(name = "payee.phoneNumber")
    private WebElement payeePhoneField;

    @FindBy(name = "payee.accountNumber")
    private WebElement accountNumberField;

    @FindBy(name = "verifyAccount")
    private WebElement verifyAccountField;

    @FindBy(name = "amount")
    private WebElement amountField;

    @FindBy(name = "fromAccountId")
    private WebElement fromAccountDropdown;

    @FindBy(css = "input[value='Send Payment'], input[value='SEND PAYMENT']")
    private WebElement sendPaymentButton;

    @FindBy(css = "div#rightPanel h1.title")
    private WebElement pageTitle;

    @FindBy(css = "div#billpayResult h1")
    private WebElement resultTitle;

    @FindBy(css = "div#billpayResult p")
    private WebElement resultMessage;

    @FindBy(css = "p.error")
    private WebElement errorMessage;

    @FindBy(name = "payee.name.errors")
    private WebElement payeeNameError;

    @FindBy(name = "amount.errors")
    private WebElement amountError;

    // Constructor
    public BillPayPage(WebDriver driver) {
        super(driver);
    }

    // Page Actions
    /**
     * Check if Bill Pay page is loaded
     */
    public boolean isBillPayPageLoaded() {
        try {
            waitForElementToBeVisible(pageTitle);
            boolean isLoaded = pageTitle.getText().contains("Bill Payment");
            LoggerUtil.info("Bill Pay page loaded: " + isLoaded);
            return isLoaded;
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * Pay bill
     */
    public void payBill(String payeeName, String address, String city, String state,
                        String zipCode, String phone, String account, String amount,
                        String fromAccountIndex) {
        LoggerUtil.info("Paying bill to: " + payeeName + " - Amount: " + amount);
        
        enterText(payeeNameField, payeeName);
        enterText(payeeAddressField, address);
        enterText(payeeCityField, city);
        enterText(payeeStateField, state);
        enterText(payeeZipCodeField, zipCode);
        enterText(payeePhoneField, phone);
        enterText(accountNumberField, account);
        enterText(verifyAccountField, account);
        enterText(amountField, amount);
        
        Select fromAccount = new Select(fromAccountDropdown);
        fromAccount.selectByIndex(Integer.parseInt(fromAccountIndex));
        
        clickElement(sendPaymentButton);
    }

    /**
     * Check if payment was successful
     */
    public boolean isPaymentSuccessful() {
        try {
            waitForElementToBeVisible(resultTitle);
            String title = resultTitle.getText();
            boolean isSuccessful = title.contains("Bill Payment Complete");
            LoggerUtil.info("Payment successful: " + isSuccessful);
            return isSuccessful;
        } catch (Exception e) {
            LoggerUtil.error("Payment not successful: " + e.getMessage());
            return false;
        }
    }

    /**
     * Get error message
     */
    public String getErrorMessage() {
        try {
            return getElementText(errorMessage);
        } catch (Exception e) {
            return "";
        }
    }

    /**
     * Check if amount error is displayed
     */
    public boolean isAmountErrorDisplayed() {
        try {
            return isElementDisplayed(amountError);
        } catch (Exception e) {
            return false;
        }
    }
}
