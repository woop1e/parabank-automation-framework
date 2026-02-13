package pages;

import base.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import utils.LoggerUtil;

/**
 * HomePage - Page Object for ParaBank Home Page
 */
public class HomePage extends BasePage {

    // Page Elements
    @FindBy(css = "input[name='username']")
    private WebElement usernameField;

    @FindBy(css = "input[name='password']")
    private WebElement passwordField;

    @FindBy(css = "input[type='submit'][value='Log In']")
    private WebElement loginButton;

    @FindBy(linkText = "Register")
    private WebElement registerLink;

    @FindBy(css = "div.logo")
    private WebElement logo;

    @FindBy(linkText = "About Us")
    private WebElement aboutUsLink;

    @FindBy(linkText = "Services")
    private WebElement servicesLink;

    @FindBy(linkText = "Products")
    private WebElement productsLink;

    @FindBy(linkText = "Locations")
    private WebElement locationsLink;

    @FindBy(linkText = "Admin Page")
    private WebElement adminPageLink;

    @FindBy(css = "p.error")
    private WebElement errorMessage;

    // Constructor
    public HomePage(WebDriver driver) {
        super(driver);
    }

    // Page Actions
    /**
     * Check if home page is loaded
     */
    public boolean isHomePageLoaded() {
        try {
            boolean isLoaded = isElementDisplayed(usernameField) && isElementDisplayed(loginButton);
            LoggerUtil.info("Home page loaded status: " + isLoaded);
            return isLoaded;
        } catch (Exception e) {
            LoggerUtil.error("Error checking home page load status: " + e.getMessage());
            return false;
        }
    }

    /**
     * Login to application
     */
    public AccountOverviewPage login(String username, String password) {
        LoggerUtil.info("Attempting login with username: " + username);
        enterText(usernameField, username);
        enterText(passwordField, password);
        clickElement(loginButton);
        return new AccountOverviewPage(driver);
    }

    /**
     * Click on Register link
     */
    public RegisterPage clickRegister() {
        LoggerUtil.info("Clicking on Register link");
        clickElement(registerLink);
        return new RegisterPage(driver);
    }

    /**
     * Get error message text
     */
    public String getErrorMessage() {
        return getElementText(errorMessage);
    }

    /**
     * Check if error message is displayed
     */
    public boolean isErrorMessageDisplayed() {
        return isElementDisplayed(errorMessage);
    }

    /**
     * Navigate to About Us page
     */
    public void clickAboutUs() {
        clickElement(aboutUsLink);
    }

    /**
     * Navigate to Services page
     */
    public void clickServices() {
        clickElement(servicesLink);
    }

    /**
     * Navigate to Products page
     */
    public void clickProducts() {
        clickElement(productsLink);
    }

    /**
     * Navigate to Locations page
     */
    public void clickLocations() {
        clickElement(locationsLink);
    }

    /**
     * Check if navigation link is displayed
     */
    public boolean isNavigationLinkDisplayed(String linkName) {
        boolean isDisplayed = false;
        switch (linkName.toLowerCase()) {
            case "about us":
                isDisplayed = isElementDisplayed(aboutUsLink);
                break;
            case "services":
                isDisplayed = isElementDisplayed(servicesLink);
                break;
            case "products":
                isDisplayed = isElementDisplayed(productsLink);
                break;
            case "locations":
                isDisplayed = isElementDisplayed(locationsLink);
                break;
        }
        LoggerUtil.info(linkName + " navigation link displayed: " + isDisplayed);
        return isDisplayed;
    }
}
