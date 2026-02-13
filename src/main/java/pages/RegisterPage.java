package pages;

import base.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import utils.LoggerUtil;

/**
 * RegisterPage - Page Object for Registration Page
 */
public class RegisterPage extends BasePage {

    // Page Elements
    @FindBy(id = "customer.firstName")
    private WebElement firstNameField;

    @FindBy(id = "customer.lastName")
    private WebElement lastNameField;

    @FindBy(id = "customer.address.street")
    private WebElement addressField;

    @FindBy(id = "customer.address.city")
    private WebElement cityField;

    @FindBy(id = "customer.address.state")
    private WebElement stateField;

    @FindBy(id = "customer.address.zipCode")
    private WebElement zipCodeField;

    @FindBy(id = "customer.phoneNumber")
    private WebElement phoneField;

    @FindBy(id = "customer.ssn")
    private WebElement ssnField;

    @FindBy(id = "customer.username")
    private WebElement usernameField;

    @FindBy(id = "customer.password")
    private WebElement passwordField;

    @FindBy(id = "repeatedPassword")
    private WebElement confirmPasswordField;

    @FindBy(css = "input[type='submit'][value='Register']")
    private WebElement registerButton;

    @FindBy(css = "p.error")
    private WebElement errorMessage;

    @FindBy(css = "div#rightPanel h1")
    private WebElement pageTitle;

    @FindBy(css = "div#rightPanel p")
    private WebElement successMessage;

    @FindBy(id = "customer.firstName.errors")
    private WebElement firstNameError;

    @FindBy(id = "customer.lastName.errors")
    private WebElement lastNameError;

    @FindBy(id = "customer.username.errors")
    private WebElement usernameError;

    // Constructor
    public RegisterPage(WebDriver driver) {
        super(driver);
    }

    // Page Actions
    /**
     * Register new user
     */
    public void registerUser(String firstName, String lastName, String address, String city,
                             String state, String zipCode, String phone, String ssn,
                             String username, String password) {
        LoggerUtil.info("Registering new user: " + username);
        
        enterText(firstNameField, firstName);
        enterText(lastNameField, lastName);
        enterText(addressField, address);
        enterText(cityField, city);
        enterText(stateField, state);
        enterText(zipCodeField, zipCode);
        enterText(phoneField, phone);
        enterText(ssnField, ssn);
        enterText(usernameField, username);
        enterText(passwordField, password);
        enterText(confirmPasswordField, password);
        
        clickElement(registerButton);
    }

    /**
     * Click register button without filling fields
     */
    public void clickRegisterWithoutData() {
        LoggerUtil.info("Clicking register button without filling data");
        clickElement(registerButton);
    }

    /**
     * Check if registration was successful
     */
    public boolean isRegistrationSuccessful() {
        try {
            waitForElementToBeVisible(pageTitle);
            String titleText = pageTitle.getText();
            boolean isSuccessful = titleText.contains("Welcome") || titleText.contains("successfully created");
            LoggerUtil.info("Registration successful: " + isSuccessful);
            return isSuccessful;
        } catch (Exception e) {
            LoggerUtil.error("Registration not successful: " + e.getMessage());
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
     * Check if username already exists error is displayed
     */
    public boolean isUsernameAlreadyExistsErrorDisplayed() {
        try {
            waitForElementToBeVisible(usernameError);
            String error = usernameError.getText();
            boolean isDisplayed = error.contains("already exists");
            LoggerUtil.info("Username already exists error displayed: " + isDisplayed);
            return isDisplayed;
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * Check if required field errors are displayed
     */
    public boolean areRequiredFieldErrorsDisplayed() {
        try {
            boolean firstNameErrorDisplayed = isElementDisplayed(firstNameError);
            boolean lastNameErrorDisplayed = isElementDisplayed(lastNameError);
            boolean isDisplayed = firstNameErrorDisplayed && lastNameErrorDisplayed;
            LoggerUtil.info("Required field errors displayed: " + isDisplayed);
            return isDisplayed;
        } catch (Exception e) {
            return false;
        }
    }
}
