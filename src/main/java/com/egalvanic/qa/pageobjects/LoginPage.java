package com.egalvanic.qa.pageobjects;

import com.egalvanic.qa.testcase.BaseTest;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

/**
 * Page Object Model for Login Page
 */
public class LoginPage extends BaseTest {
    
    // Locators
    private By emailField = By.id("email");
    private By passwordField = By.id("password");
    private By loginButton = By.xpath("//button[@type='submit' or contains(.,'Sign in') or contains(.,'Login')]");
    private By errorMessage = By.xpath("//div[contains(@class,'error') or contains(@class,'alert') or contains(text(),'Incorrect')]");
    
    /**
     * Enter email in the email field
     * @param email Email to enter
     */
    public void enterEmail(String email) {
        type(emailField, email);
    }
    
    /**
     * Enter password in the password field
     * @param password Password to enter
     */
    public void enterPassword(String password) {
        type(passwordField, password);
    }
    
    /**
     * Click the login button
     */
    public void clickLoginButton() {
        click(loginButton);
    }
    
    /**
     * Get the error message element
     * @return Error message WebElement
     */
    public WebElement getErrorMessage() {
        return visible(errorMessage, DEFAULT_TIMEOUT);
    }
    
    /**
     * Check if error message is displayed
     * @return True if error message is displayed, false otherwise
     */
    public boolean isErrorMessageDisplayed() {
        try {
            return getErrorMessage().isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }
    
    /**
     * Perform login with email and password
     * @param email Email to login with
     * @param password Password to login with
     */
    public void login(String email, String password) {
        enterEmail(email);
        enterPassword(password);
        clickLoginButton();
    }
}