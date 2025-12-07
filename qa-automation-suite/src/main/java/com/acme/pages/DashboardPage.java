package com.acme.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.support.ui.Select;

import java.time.Duration;

public class DashboardPage {
    private WebDriver driver;
    private WebDriverWait wait;
    
    // Constructor
    public DashboardPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        PageFactory.initElements(driver, this);
    }
    
    // Page elements with more specific locators
    @FindBy(xpath = "//h1[contains(text(), 'Platform')] | //div[contains(@class, 'MuiBox-root') and contains(., 'Platform')]")
    private WebElement dashboardHeader;
    
    // Updated locators for better dropdown identification
    @FindBy(xpath = "//div[contains(@class, 'MuiSelect-root') or contains(@class, 'site-selector') or @aria-label='Site Selector' or @id='site-selector' or contains(@class, 'MuiInputBase-root')][.//div[contains(@class, 'MuiSelect-select') or contains(text(), 'Select Site') or contains(text(), 'Test Site')]]")
    private WebElement siteDropdown;
    
    @FindBy(xpath = "//button[contains(text(), 'Logout') or contains(text(), 'Sign out') or contains(text(), 'Log out')] | //a[contains(text(), 'Logout') or contains(text(), 'Sign out') or contains(text(), 'Log out')]")
    private WebElement logoutButton;
    
    // Methods
    public boolean isDashboardDisplayed() {
        try {
            wait.until(ExpectedConditions.visibilityOf(dashboardHeader));
            return dashboardHeader.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }
    
    public String getDashboardHeaderText() {
        if (isDashboardDisplayed()) {
            return dashboardHeader.getText();
        }
        return "";
    }
    
    public boolean isSiteDropdownPresent() {
        try {
            // Wait for the dropdown to be present and visible
            wait.until(ExpectedConditions.visibilityOf(siteDropdown));
            return siteDropdown.isDisplayed();
        } catch (Exception e) {
            System.err.println("Site dropdown not found: " + e.getMessage());
            return false;
        }
    }
    
    public boolean selectTestSite() {
        if (isSiteDropdownPresent()) {
            try {
                // Scroll to the dropdown to ensure it's visible
                JavascriptExecutor js = (JavascriptExecutor) driver;
                js.executeScript("arguments[0].scrollIntoView({block: 'center'});", siteDropdown);
                
                // Wait for any animations to complete
                Thread.sleep(1000);
                
                // Click to expand the dropdown
                siteDropdown.click();
                
                // Wait for options to appear
                Thread.sleep(1000);
                
                // Try to find and click the "Test Site" option using multiple strategies
                try {
                    // Strategy 1: Look for text containing "Test Site"
                    WebElement testSiteOption = driver.findElement(
                        By.xpath("//li[contains(text(), 'Test Site')] | //div[contains(text(), 'Test Site') and contains(@class, 'option')] | //option[contains(text(), 'Test Site')] | //*[@role='option' and contains(text(), 'Test Site')]"));
                    
                    if (testSiteOption != null && testSiteOption.isDisplayed()) {
                        // Scroll to the option and click
                        js.executeScript("arguments[0].scrollIntoView({block: 'center'});", testSiteOption);
                        Thread.sleep(500);
                        testSiteOption.click();
                        return true;
                    }
                } catch (Exception ex) {
                    System.err.println("Could not find Test Site option with first strategy: " + ex.getMessage());
                    
                    // Strategy 2: Try to find any option and select it
                    try {
                        WebElement anyOption = driver.findElement(
                            By.xpath("//li | //div[@role='option'] | //option"));
                        
                        if (anyOption != null && anyOption.isDisplayed()) {
                            js.executeScript("arguments[0].scrollIntoView({block: 'center'});", anyOption);
                            Thread.sleep(500);
                            anyOption.click();
                            return true;
                        }
                    } catch (Exception ex2) {
                        System.err.println("Could not find any option: " + ex2.getMessage());
                        
                        // Strategy 3: Try typing the value
                        siteDropdown.sendKeys("Test Site");
                        siteDropdown.sendKeys(Keys.ENTER);
                        return true;
                    }
                }
                
            } catch (Exception e) {
                System.err.println("Could not select Test Site from dropdown: " + e.getMessage());
                e.printStackTrace();
                return false;
            }
        }
        System.err.println("Site dropdown not present, cannot select Test Site");
        return false;
    }
    
    public void clickLogout() {
        try {
            wait.until(ExpectedConditions.elementToBeClickable(logoutButton));
            logoutButton.click();
        } catch (Exception e) {
            System.err.println("Logout button not found or clickable: " + e.getMessage());
        }
    }
    
    public boolean isLoggedIn() {
        return isDashboardDisplayed();
    }
}